public class University {
    private String name;
    private Student[] students;
    private int noStudents;

    public University() {
        name = "University of Computing";
        students = new Student[100];
        noStudents = 0;
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
    public static void main(String[] args) {
        University u = new University();
        System.out.println(u.getName());
        u.addStudent("Joe", "Stewart", "650-417-5232");
        System.out.println(u.getNoStudents() + " students enrolled");
        u.changePhone(10, "650-938-6396");
        System.out.println(u.getStudents());
    }
}
