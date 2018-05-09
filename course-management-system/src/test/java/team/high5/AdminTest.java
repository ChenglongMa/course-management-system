package team.high5;

import org.junit.*;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Admin;
import team.high5.domain.user.Lecturer;
import team.high5.domain.user.Student;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class AdminTest {
    private static CourseOffering offeringMock;

    private static Lecturer lecturerMock;

    private static Schedule currentSchedule;

    private static Student studentMock;


    private static int defaultSemester;
    private static int defaultWeek;
    private static int defaultYear;

    private Admin adminToTest;

    @BeforeClass
    public static void setUpClass() {
        defaultSemester = 1;
        defaultWeek = 1;
        defaultYear = 2018;
        offeringMock = mock(CourseOffering.class);
//        offeringMock.getCapacity()
        lecturerMock = mock(Lecturer.class);
        when(lecturerMock.getName()).thenReturn("Doctor Who");
        currentSchedule = mock(Schedule.class);
        studentMock = mock(Student.class);
    }

    @AfterClass
    public static void tearDownClass() {
        offeringMock = null;
        lecturerMock = null;
        currentSchedule = null;

    }

    @Before
    public void setUp() throws Exception {
        adminToTest = new Admin();
    }

    @After
    public void tearDown() throws Exception {
        adminToTest = null;
    }

    @Test
    public void addCourseOffering() {
//        CourseOffering actual = adminToTest.addCourseOffering(offeringMock);
//        assertEquals(offeringMock, actual);
    }

    @Test(expected = NullPointerException.class)
    public void addNullOffering() {
//        adminToTest.addCourseOffering(null);
    }

    @Test
    public void addOfferingReturnOthers() {
        CourseOffering unexpected = new CourseOffering();
//        assertNotEquals(unexpected, adminToTest.addCourseOffering(offeringMock));
    }

    @Test
    public void assignLecturerTest() {
        CourseOffering tarOffering = mock(CourseOffering.class);
//        stub(tarOffering.getLecturer()).toReturn(lecturerMock);
//        CourseOffering offering = adminToTest.assignLecturer(tarOffering, lecturerMock);
//        assertEquals(tarOffering, offering);
//        assertEquals(lecturerMock.getName(), offering.getLecturer().getName());
    }
//
//    @Test
//    public void viewPastPerformance() {
//        ArrayList<Enrolment> expected = new ArrayList<>();
//        when(offeringMock.getSchedule()).thenReturn(Schedule.currentSchedule());
//        Enrolment enrolment = mock(Enrolment.class);
//        when(enrolment.getOffering()).thenReturn(offeringMock);
//        when(enrolment.getStudent()).thenReturn(studentMock);
//        expected.add(enrolment);
//        when(studentMock.getPerformance()).thenReturn(expected);
//        assertNotEquals(expected, adminToTest.viewPastPerformance(studentMock));
//    }
//
//    @Test
//    public void viewPastPerformance2() {
//        ArrayList<Enrolment> expected = new ArrayList<>();
//        when(offeringMock.getSchedule()).thenReturn(new Schedule());
//        Enrolment enrolment = mock(Enrolment.class);
//        when(enrolment.getOffering()).thenReturn(offeringMock);
//        when(enrolment.getStudent()).thenReturn(studentMock);
//        expected.add(enrolment);
//        when(studentMock.getPerformance()).thenReturn(expected);
//        assertEquals(expected, adminToTest.viewPastPerformance(studentMock));
//    }
}