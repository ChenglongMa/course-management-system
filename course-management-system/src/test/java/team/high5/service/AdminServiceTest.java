package team.high5.service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.domain.user.Lecturer;

import static org.hamcrest.Matchers.greaterThan;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 18-05-2018
 * @Time : 15:41
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdminService adminService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseOfferingService offeringService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private LecturerService lecturerService;
    private Course course;
    private CourseOffering offering;
    private Schedule currSchedule;

    @BeforeClass
    public static void beforeSetUp() {

    }

    @AfterClass
    public static void afterTearDown() {

    }

    @Before
    public void setUp() throws Exception {
        currSchedule = scheduleService.findCurrentSchedule();
        // prepared data
        course = new Course();
        course.setCode("ISYS1117");
        course.setName("SEF");
        course.setMainTopic("Software Engineering Fundamentals");
        course = courseService.save(course);
        logger.debug("Create new course " + course.getCode());
        offering = new CourseOffering();
        offering.setCourse(course);
        offering.setCapacity(100);
        offering.setYear(currSchedule.getYear());
        offering.setSemester(currSchedule.getSemester());
        offeringService.save(offering);
    }

    @After
    public void tearDown() throws Exception {
        offeringService.deleteIfExist(offering);
        courseService.deleteIfExist(course);
        Schedule.setCurrentSchedule(currSchedule);
        scheduleService.saveCurrentSchedule();
        logger.debug("Clean temp data.");
    }

    @Test
    public void addCourseOffering() {
        if (offeringService.deleteIfExist(offering)) {
            logger.debug("Delete existed offering");
        }
        offering.setOfferingId(-1);
        Assert.assertEquals(-1, offering.getOfferingId());
        offering = adminService.addCourseOffering(offering);
        logger.debug("Add new Course Offering " + offering.getOfferingId());
        Assert.assertThat(offering.getOfferingId(), greaterThan(0));
    }

    @Test
    public void assignLecturer() {
        Lecturer lecturer = new Lecturer("e1001", "123");
        Lecturer lecturerExp = lecturerService.findOne(lecturer);
        if (lecturerExp == null) {
            lecturer = lecturerService.insert(lecturer);
        } else lecturer = lecturerExp;
        Assert.assertNotEquals(lecturer, offering.getLecturer());
        offering = adminService.assignLecturer(offering, lecturer);
        Assert.assertEquals(lecturer, offering.getLecturer());
    }

    @Test
    public void advanceSystem() {
        for (int i = 0; i < 25; i++) {
            int year = Schedule.currentSchedule().getYear();
            int semester = Schedule.currentSchedule().getSemester();
            int week = Schedule.currentSchedule().getWeek();
            adminService.advanceSystem();
            int nYear = Schedule.currentSchedule().getYear();
            int nSem = Schedule.currentSchedule().getSemester();
            int nWeek = Schedule.currentSchedule().getWeek();
            Assert.assertEquals(Schedule.currentSchedule(), scheduleService.findCurrentSchedule());
            Assert.assertEquals(nWeek, scheduleService.findCurrentSchedule().getWeek());
            if (week < Schedule.WEEK_COUNT) {
                Assert.assertEquals(1, nWeek - week);
                Assert.assertEquals(nYear, year);
                Assert.assertEquals(nSem, semester);
            } else if (semester < Schedule.SEMESTER_COUNT) {
                Assert.assertEquals(1, nWeek);
                Assert.assertEquals(1, nSem - semester);
                Assert.assertEquals(nYear, year);
            } else {
                Assert.assertEquals(1, nWeek);
                Assert.assertEquals(1, nSem);
                Assert.assertEquals(1, nYear - year);
            }
        }
    }
}