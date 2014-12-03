public class Course {
    private String code;
    private String title;
    private int noCredits;
    private int maxStudents;

    public Course(String code, String title, int noCredits, int maxStudents) {
        this.code = code;
        this.title = title;
        this.noCredits = noCredits;
        this.maxStudents = maxStudents;
    }

    public boolean equals(Object o) {
        if(o instanceof Course) {
            Course c = (Course) o;
            if(this.code.equals(c.getCode()))
                return true;
            else
                return false;
        }
        else
            throw new IllegalArgumentException("Argument is not of type Course.");
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getNumCredits() {
        return noCredits;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumCredits(int noCredits) {
        this.noCredits = noCredits;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public String toString() {
        String s;

        s = "Course code: " + code + " Title: " + title + " Credits: " + noCredits + "Enrollment Limit: " + maxStudents;

        return s;
    }

    public static void main(String[] args) {
    }
}
