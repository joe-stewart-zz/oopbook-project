public class UndergraduateStudent extends StudentRole {
    private String major;
    private String minor;

    public UndergraduateStudent(Student student, String roleName, String major, String minor) {
        super(roleName, student);
        this.major = major;
        this.minor = minor;
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String toString() {
        String s;

        s = this.getStudent().toString() + " Major: " + major + " Minor: " + minor;

        return s;
    }
}
