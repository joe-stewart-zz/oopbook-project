public abstract class StudentRole {
    private String name;
    private Student student;

    public StudentRole(String name, Student student) {
        this.name = name;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public Student getStudent() {
        return student;
    }

    public boolean equals(Object obj) {
        if(obj instanceof StudentRole) {
            StudentRole sr = (StudentRole) obj;
            if(name.equals(sr.getName()))
                return true;
            else
                return false;
        } else {
            throw new IllegalArgumentException("Argument not of type StudentRole.");
        }
    }
}
