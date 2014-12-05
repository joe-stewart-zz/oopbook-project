import java.util.*;

public class University {
    private static final int MAX_CREDITS = 20;

    private static University university = null;

    private String name;
    private ArrayList students;
    private ArrayList courses;
    private ArrayList registrations;

    private University(String name) {
        this.name = name;
        students = new ArrayList();
        courses = new ArrayList();
        registrations = new ArrayList();
    }

    public static University getInstance() {
        if(university == null)
            university = new University("The University of Computing");
        return university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course addCourse(String code, String title, int noCredits, int maxStudents) {
        Course course = getCourse(code);
        if(course == null) {
            course = new Course(code, title, noCredits, maxStudents);
            courses.add(course);
            return course;
        }
        else
            return null;
    }

    public Course updateCourse(String code, String title, int noCredits, int maxStudents) {
        Course course = getCourse(code);
        if(course != null) {
            course.setTitle(title);
            course.setNumCredits(noCredits);
            course.setMaxStudents(maxStudents);
            return course;
        }
        else
            return null;
    }

    public UndergraduateStudent addUndergraduateStudent(long ID, String name, String address, String phone, String email, String major, String minor) {
        Student s = getStudent(ID);
        if(s != null)
            return null;
        UndergraduateStudent student = new UndergraduateStudent(ID, name, address, phone, email, major, minor);
        students.add(student);
        return student;
    }

    public UndergraduateStudent updateUndergraduateStudent(long ID, String name, String address, String phone, String email, String major, String minor) {
        UndergraduateStudent s = (UndergraduateStudent) getStudent(ID);
        if(s == null)
            return null;
        s.setName(name);
        s.setAddress(address);
        s.setPhone(phone);
        s.setEmail(email);
        s.setMajor(major);
        s.setMinor(minor);
        return s;
    }

    public PostgraduateStudent addPostgraduateStudent(long ID, String name, String address, String phone, String email, String thesisTitle, String supervisor) {
        Student s = getStudent(ID);
        if(s != null)
            return null;
        PostgraduateStudent student = new PostgraduateStudent(ID, name, address, phone, email, thesisTitle, supervisor);
        students.add(student);
        return student;
    }

    public PostgraduateStudent updatePostgraduateStudent(long ID, String name, String address, String phone, String email, String thesisTitle, String supervisor) {
        PostgraduateStudent s = (PostgraduateStudent) getStudent(ID);
        if(s == null)
            return null;
        s.setName(name);
        s.setAddress(address);
        s.setPhone(phone);
        s.setEmail(email);
        s.setThesisTitle(thesisTitle);
        s.setSupervisor(supervisor);
        return s;
    }

    public Course getCourse(String code) {
        for(int i = 0; i < courses.size(); i++) {
            Course c = (Course) courses.get(i);
            if(c.getCode().equals(code))
                return c;
        }
        return null;
    }

    public Student getStudent(long ID) {
        for(int i = 0; i < students.size(); i++) {
            Student s = (Student) students.get(i);
            if(s.getID() == ID)
                return s;
        }
        return null;
    }

    public ArrayList getCourses(long studentID) {
        ArrayList result = new ArrayList();
        for(int i = 0; i < registrations.size(); i++) {
            Registration r = (Registration) registrations.get(i);
            if(r.getStudent().getID() == studentID)
                result.add(r.getCourse());
        }
        return result;
    }

    public ArrayList getStudents(String courseCode) {
        ArrayList result = new ArrayList();
        for(int i = 0; i < registrations.size(); i++) {
            Registration r = (Registration) registrations.get(i);
            if(r.getCourse().getCode().equals(courseCode))
                result.add(r.getStudent());
        }
        return result;
    }

    public Registration getRegistration(long studentID, String courseCode) {
        for(int i = 0; i < registrations.size(); i++) {
            Registration r = (Registration) registrations.get(i);
            if(r.getStudent().getID() == studentID && r.getCourse().getCode().equals(courseCode))
                return r;
        }
        return null;
    }

    private int getCredits(ArrayList courses) {
        int numCredits = 0;
        for(int i = 0; i < courses.size(); i++) {
            Course c = (Course) courses.get(i);
            numCredits = numCredits + c.getNumCredits();
        }
        return numCredits;
    }

    public boolean registerStudent(long studentID, String courseCode) throws TooManyCreditsException, CourseFullException {
        ArrayList studentCourses;
        ArrayList courseStudents;
        Student student = getStudent(studentID);
        Course course = getCourse(courseCode);
        if(student == null || course == null)
            return false;
        studentCourses = getCourses(studentID);
        courseStudents = getStudents(courseCode);
        if(getCredits(studentCourses) + course.getNumCredits() > MAX_CREDITS)
            throw new TooManyCreditsException();
        if(courseStudents.size() == course.getMaxStudents())
            throw new CourseFullException();
        Registration registration = new Registration(student, course);
        registrations.add(registration);
        return true;
    }

    public boolean deRegisterStudent(long studentID, String courseCode) {
        Registration registration = getRegistration(studentID, courseCode);
        if(registration == null)
            return false;
        registrations.remove(registration);
        return true;
    }

    public String getStudents() {
        String output = "";
        for(int i = 0; i < students.size(); i++) {
            String type;
            Student s = (Student) students.get(i);
            output = output + s.getID() + ", " + s.getName() + ", ";
            if(s instanceof UndergraduateStudent)
                type = "Undergraduate";
            else
                type = "Postgraduate";
            output = output + type + "\n";
        }
        output = "There are " + students.size() + " students: " + "\n\n" + output;
        return output;
    }

    public String getUndergraduateStudents() {
        String output = "";
        int count = 0;
        for(int i = 0; i < students.size(); i++) {
            String type;
            Student s = (Student) students.get(i);
            if(s instanceof UndergraduateStudent) {
                output = output + s.getID() + ", " + s.getName() + "\n";
                count++;
            }
        }
        output = "There are " + count + " undergraduate students: " + "\n\n" + output;
        return output;
    }

    public String getPostgraduateStudents() {
        String output = "";
        int count = 0;
        for(int i = 0; i < students.size(); i++) {
            String type;
            Student s = (Student) students.get(i);
            if(s instanceof PostgraduateStudent) {
                output = output + s.getID() + ", " + s.getName() + "\n";
                count++;
            }
        }
        output = "There are " + count +" postgraduate students: " + "\n\n" + output;
        return output;
    }

    public String getCourses() {
        String output = "";
        for(int i = 0; i < courses.size(); i++) {
            Course c = (Course) courses.get(i);
            output = output + c.getCode() + ", " + c.getTitle() + ", " + c.getNumCredits() + " credits\n";
        }
        output = "There are " + courses.size() + " courses: " + "\n\n" + output;
        return output;
    }

    public static void main(String[] args) {
    }
}
