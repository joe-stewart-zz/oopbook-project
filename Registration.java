public class Registration {
    private Student student;
    private Course course;
    private int academicYear;
    private int semester;

    public Registration(Student student, Course course, int academicYear, int semester) {
        this.student = student;
        this.course = course;
        this.academicYear = academicYear;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public int getSemester() {
        return semester;
    }
}
