public class Student {
    private int ID;
    private String firstName;
    private String lastName;
    private String phone;
    private static int IDGenerator = 10;
    public Student(String firstName, String lastName, String phone) {
        ID = IDGenerator;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        IDGenerator += 10;
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
    public static void main(String[] args) {
        Student s = new Student("Joe", "Stewart", "650-938-6396");
        System.out.println(s);
        s.setPhone("650-417-5232");
        System.out.println(s);
    }
}
