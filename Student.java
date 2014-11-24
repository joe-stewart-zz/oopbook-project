public class Student {
    private int ID;
    private String firstName;
    private String lastName;
    private String phone;
    private static int IDGenerator = 10;
    private static int MAX_COURSES = 30;
    private Course[] courses;
    private int noCourses;

    public Student(String firstName, String lastName, String phone) {
        ID = IDGenerator;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        IDGenerator += 10;
        courses = new Course[MAX_COURSES];
        noCourses = 0;
    }
    public int getID() {
        return ID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhone() {
        return phone;
    }
    public String toString() {
        String s;
        s = "ID: " + ID + " Name: " + firstName + " " + lastName + " Telephone: " + phone;
        return s;
    }
    public void setPhone(String newPhone) {
        phone = newPhone;
    }
    public boolean register(Course course) {
        if(noCourses == MAX_COURSES)
            return false;
        for(int i = 0; i < noCourses; i++)
            if(courses[i] == course)
                return false;
        courses[noCourses] = course;
        noCourses++;
        return true;
    }
    public String getCourses() {
        String s;
        s = "Courses registered for:\n";
        for(int i = 0; i < noCourses; i++)
            s = s + courses[i].toString() + "\n";
        return s;
    }
    public static void main(String[] args) {
        Student s = new Student("Joe", "Stewart", "650-938-6396");
        System.out.println(s);
        s.setPhone("650-417-5232");
        System.out.println(s);
    }
}
