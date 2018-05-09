package team.high5;

import org.junit.*;
import team.high5.domain.user.User;


import static org.junit.Assert.assertEquals;

public class UserTest {
    static class innerUser extends User {

    }

    User userToTest;

    private static String defaultName;
    private static String defaultPwd;
    private static String defaultId;
    @BeforeClass
    public static void setupClass() {
        defaultName = "defaultName";
        defaultPwd = "defaultPwd";
        defaultId = "defaultID";
    }
    @Before
    public void setUp() throws Exception {
        userToTest = new innerUser();
        userToTest.setUserId(defaultId);
        userToTest.setName(defaultName);
        userToTest.setPassword(defaultPwd);
    }

    @After
    public void tearDown() throws Exception {
        userToTest = null;
    }

    @AfterClass
    public static void tearDownClass() {
        defaultName = null;
        defaultPwd = null;
        defaultId = null;
    }
    @Test
    public void getUserId() {
        String expected = defaultId;
        assertEquals(expected, userToTest.getUserId());
    }

    @Test
    public void setUserId() {
        String expected = "userId";
        userToTest.setUserId(expected);
        assertEquals(expected,userToTest.getUserId());
    }

    @Test
    public void getName() {
        String expected = defaultName;
        assertEquals(expected, userToTest.getName());
    }

    @Test
    public void setName() {
        String expected = "userName";
        userToTest.setName(expected);
        assertEquals(expected, userToTest.getName());
    }

    @Test
    public void getPassword() {
        String expectedPwd = "somepassword";
        userToTest.setPassword(expectedPwd);
        String actualPwd = userToTest.getPassword();
        assertEquals(expectedPwd, actualPwd);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setIllegalPassword() {
        String illegalPwd = "_illegal_passW0rd";
        userToTest.setPassword(illegalPwd);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDuplicatedPassword() {
        userToTest.setPassword(userToTest.getPassword());
    }
}