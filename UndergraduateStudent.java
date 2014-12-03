public class UndergraduateStudent extends Student {
    private String major;
    private String minor;

    public UndergraduateStudent(long ID, String name, String address, String phone, String email, String major, String minor) {
        super(ID, name, address, phone, email);
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

        s = super.toString() + " Major: " + major + " Minor: " + minor;

        return s;
    }
}
