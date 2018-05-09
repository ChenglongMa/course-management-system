package team.high5;

import org.junit.*;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Enrolment;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Lecturer;
import team.high5.domain.user.Student;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class LecturerTest {

    private static CourseOffering offeringMock;

    private static Lecturer testLecturer;
    private static Student studentMock;

    private static Schedule currentSche;
    private static Enrolment enrolmentMock;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        studentMock = mock(Student.class);
        offeringMock = mock(CourseOffering.class);
        currentSche = mock(Schedule.class);
        enrolmentMock = mock(Enrolment.class);

    }

    @AfterClass
    public static void setUpAfterClass() {
        studentMock = null;
        offeringMock = null;
        currentSche = null;
        enrolmentMock = null;
    }

    @Before
    public void setUp() throws Exception {
        testLecturer = new Lecturer();
    }

    @After
    public void tearDown() throws Exception {
        testLecturer = null;
    }

    @Test
    public void uploadResult() {
        when(offeringMock.getLecturer()).thenReturn(testLecturer);
        when(offeringMock.getSchedule()).thenReturn(Schedule.currentSchedule());
        when(enrolmentMock.getOffering()).thenReturn(offeringMock);
        when(enrolmentMock.getStudent()).thenReturn(studentMock);
        List<Enrolment> enrolments = new ArrayList<>();
        enrolments.add(enrolmentMock);
        when(studentMock.getPerformance()).thenReturn(enrolments);
//        testLecturer.uploadResult(studentMock, "HD");
        verify(enrolmentMock).setResult("HD");
    }

    @Test(expected = NullPointerException.class)
    public void uploadResultNullException() {
        List<Enrolment> test = new ArrayList<>();
        when(studentMock.getPerformance()).thenReturn(test);
//        testLecturer.uploadResult(studentMock, "HD");
    }
}