package team.high5;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ScheduleTest {

    private static Schedule currentSchedule;

    @Before
    public void setUp() throws Exception {
        currentSchedule = Schedule.currentSchedule();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void advance() {
    }

    private void setCurrentSchedule(int week, int semester, int year) {
        when(currentSchedule.getWeek()).thenReturn(week);
        when(currentSchedule.getSemester()).thenReturn(semester);
        when(currentSchedule.getYear()).thenReturn(year);
    }

    //    @Ignore
    @Test
    public void advanceWeek() {
        int week = 1, semester = 1, year = 2018;
        for (week = 1; week < 14; week++) {
            setCurrentSchedule(week, semester, year);
            currentSchedule.advance();
            if (week < 12) {
                verify(currentSchedule).setWeek(week + 1);
            } else {
                verify(currentSchedule, times(week - 11)).setWeek(1);
            }
        }
        verify(currentSchedule, times(2)).setSemester(semester + 1);
    }

    @Test
    public void advanceSemester() {
        int week = 12, semester = 1, year = 2018;
        for (semester = 1; semester < 5; semester++) {
            setCurrentSchedule(week, semester, year);
            currentSchedule.advance();
            if (semester < 2) {
                verify(currentSchedule, atLeastOnce()).setSemester(semester + 1);//bug
            } else {
                verify(currentSchedule, times(semester - 1)).setSemester(1);
                verify(currentSchedule, times(semester - 1)).setYear(year + 1);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void advanceWeekException() {
        when(currentSchedule.getWeek()).thenReturn(-1);
        when(currentSchedule.getSemester()).thenReturn(1);
        when(currentSchedule.getYear()).thenReturn(2018);
        currentSchedule.advance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void advanceScheduleException() {
        when(currentSchedule.getWeek()).thenReturn(12);
        when(currentSchedule.getSemester()).thenReturn(-1);
        when(currentSchedule.getYear()).thenReturn(2018);
        currentSchedule.advance();
    }
}