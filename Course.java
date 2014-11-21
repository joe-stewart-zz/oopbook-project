public class Course {
    private String code;
    private String title;
    private int noCredits;

    public Course(String code, String title, int noCredits) {
        this.code = code;
        this.title = title;
        this.noCredits = noCredits;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getNoCredits() {
        return noCredits;
    }

    public String toString() {
        String s;

        s = "Course code: " + code + " Title: " + title + " Credits: " + noCredits;

        return s;
    }
    public static void main(String[] args) {
        Course c = new Course("12345", "Programming Java", 4);
        System.out.println("code: " + c.getCode());
        System.out.println("title: " + c.getTitle());
        System.out.println("credits: " + c.getNoCredits());
        System.out.println(c.toString());
    }
}
