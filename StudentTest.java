import org.junit.*;
import static org.junit.Assert.*;

public class StudentTest {
    private Student student;

    @Before
    public void setup() {
        student = new Student("Joe", "Stewart", "417-5232");
    }

    @Test
    public void testCreate() {
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        String phone = student.getPhone();

        assertEquals("Joe", firstName);
        assertEquals("Stewart", lastName);
        assertEquals("417-5232", phone);
    }

    @Test
    public void testID() {
        Student newStudent;
        int ID1, ID2;

        newStudent = new Student("John", "Doe", "867-5309");
        ID1 = student.getID();
        ID2 = newStudent.getID();

        assertTrue(ID2 == (ID1 + 10));
    }

    @Test
    public void testSetPhone() {
        String newPhone = "938-6396";
        String phone;

        student.setPhone(newPhone);
        phone = student.getPhone();

        assertEquals(newPhone, phone);
    }

    @Test
    public void testToString() {
        int ID = student.getID();
        String actual = student.toString();
        String expected = "ID: " + ID + " Name: Joe Stewart Telephone: 417-5232";

        assertEquals(expected, actual);
    }

    @After
    public void teardown() {
        student = null;
    }
}
