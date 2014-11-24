public class University {
    private String name;
    private Student[] students;
    private int noStudents;
    private Course[] courses;
    private int noCourses;

    public University(String name) {
        this.name = name;
        students = new Student[100];
        noStudents = 0;
        courses = new Course[100];
        noCourses = 0;
    }
    public String getName() {
        return name;
    }
    public int getNoStudents() {
        return noStudents;
    }
    public Student addStudent(String firstName, String lastName, String phone) {
        Student student = new Student(firstName, lastName, phone);
        students[noStudents] = student;
        noStudents++;
        return student;
    }
    public Student getStudent(int studentID) {
        for(int i = 0; i < noStudents; i++) {
            if(students[i].getID() == studentID)
                return students[i];
        }
        return null;
    }
    public boolean changePhone(int studentID, String newPhone) {
        Student student;
        student = getStudent(studentID);
        if(student == null)
            return false;
        else {
            student.setPhone(newPhone);
            return true;
        }
    }
    public String getStudents() {
        String s = "Student List:\n";
        for(int i = 0; i < noStudents; i++)
            s = s + students[i].toString() + "\n";
        return s;
    }
    public void addCourse(String code, String title, int noCredits) {
        courses[noCourses] = new Course(code, title, noCredits);
        noCourses++;
    }
    public Course getCourse(String courseCode) {
        for(int i = 0; i < noCourses; i++)
            if(courses[i].getCode().equals(courseCode))
                return courses[i];
        return null;
    }
    public String getCourses() {
        String s = "Course List:\n";
        for(int i = 0; i < noCourses; i++)
            s = s + courses[i].toString() + "\n";
        return s;
    }
    public boolean registerStudent(int studentID, String courseCode) {
        Student student = getStudent(studentID);
        Course course = getCourse(courseCode);
        if(student == null || course == null)
            return false;
        boolean success = student.register(course);
        return success;
    }
    public String getCourses(int studentID) {
        Student student = getStudent(studentID);
        if(student == null)
            return null;
        else
            return student.getCourses();
    }
    public static void main(String[] args) {
        University u = new University("University of Computing");
        System.out.println(u.getName());
        u.addStudent("Joe", "Stewart", "650-417-5232");
        System.out.println(u.getNoStudents() + " students enrolled");
        u.changePhone(10, "650-938-6396");
        System.out.println(u.getStudents());
    }
}
