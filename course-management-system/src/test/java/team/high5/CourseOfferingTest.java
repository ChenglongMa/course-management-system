package team.high5;

import org.junit.*;
import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Lecturer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CourseOfferingTest {

    private static Lecturer lecturer;
    private static Course course;
    private static Schedule schedule;
    private CourseOffering offering;

    @BeforeClass
    public static void beforeClass() {
        lecturer = mock(Lecturer.class);
        course = mock(Course.class);
        schedule = mock(Schedule.class);
    }

    @AfterClass
    public static void afterClass() {
        lecturer = null;
        course = null;
        schedule = null;
    }

    @Before
    public void setUp() throws Exception {
        offering = new CourseOffering();
    }

    @After
    public void tearDown() throws Exception {
        offering = null;
    }

    @Test
    public void getCourse() {
        offering.setCourse(course);
        assertEquals(course, offering.getCourse());
    }

    @Test(expected = NullPointerException.class)
    public void setNullCourse() {
        offering.setCourse(null);
    }

    @Test
    public void getLecturer() {
        offering.setLecturer(lecturer);
        assertEquals(lecturer, offering.getLecturer());
    }

    @Test(expected = NullPointerException.class)
    public void setNullLecturer() {
        offering.setLecturer(null);
    }

    @Test
    public void getSchedule() {
        offering.setSchedule(schedule);
        assertEquals(schedule, offering.getSchedule());
    }

    @Test(expected = NullPointerException.class)
    public void setNullSchedule() {
        offering.setSchedule(null);
    }

    @Test
    public void getCapacity() {
        int cap = 1;
        offering.setCapacity(cap);
        assertEquals(cap, offering.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setIllegalCapacity() {
        for (int cap = -100; cap < 1; cap++) {
            offering.setCapacity(cap);
        }
    }
}