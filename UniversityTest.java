import org.junit.*;
import static org.junit.Assert.*;

public class UniversityTest {
    private University university;

    @Before
    public void setup() {
        university = new University("University of Computing");
    }

    @Test
    public void testCreate() {
        String name = university.getName();
        int noStudents = university.getNoStudents();

        assertEquals("University of Computing", name);
        assertEquals(0, noStudents);
    }

    @Test
    public void testAddStudent() {
        int noStudents;
        Student student;

        student = university.addStudent("Joe", "Stewart", "123-4567");
        noStudents = university.getNoStudents();
        assertNotNull(student);
        assertEquals(1, noStudents);

        student = university.addStudent("John", "Doe", "765-4321");
        noStudents = university.getNoStudents();
        assertNotNull(student);
        assertEquals(2, noStudents);
    }

    @Test
    public void testGetStudent() {
        Student student1, student2;

        student1 = university.addStudent("Joe", "Stewart", "123-4567");
        student2 = university.getStudent(student1.getID());

        assertNotNull(student2);
        assertEquals(student1, student2);

        student2 = university.getStudent(student1.getID() + 10);

        assertNull(student2);
    }

    @Test
    public void testChangePhone() {
        Student student;
        int ID;
        boolean success;

        student = university.addStudent("John", "Doe", "123-4567");
        ID = student.getID();
        success = university.changePhone(ID, "765-4321");

        assertEquals(true, success);
        assertEquals("765-4321", student.getPhone());

        success = university.changePhone(ID + 10, "555-1234");

        assertEquals(false, success);
    }

    @Test
    public void testGetStudents() {
        Student student1, student2;
        int ID1, ID2;

        student1 = university.addStudent("Joe", "Stewart", "123-4567");
        student2 = university.addStudent("John", "Doe", "765-4321");
        ID1 = student1.getID();
        ID2 = student2.getID();

        String expected = "Student List:\n" + "ID: " + ID1 + " Name: Joe Stewart Telephone: 123-4567\n" + "ID: " + ID2 + " Name: John Doe Telephone: 765-4321\n";
        String actual = university.getStudents();

        assertEquals(expected, actual);
    }

    @After
    public void teardown() {
        university = null;
    }
}
