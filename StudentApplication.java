import java.util.*;

public class StudentApplication {
    private static Student[] students;
    private static int noStudents;
    private static Scanner scanner;
    public static int menu() {
        int choice;

        System.out.println("\n1. Add new student");
        System.out.println("2. Display student info");
        System.out.println("3. Change student phone");
        System.out.println("4. List all students");
        System.out.println("5. Exit\n");

        choice = scanner.nextInt();
        return choice;
    }
    public static void choice1() {
        String firstName, lastName;
        String phone;
        System.out.println("Enter the student's first name, last name, and telephone number: ");
        firstName = scanner.next();
        lastName = scanner.next();
        phone = scanner.next();
        students[noStudents] = new Student(firstName, lastName, phone);
        noStudents++;
    }
    public static void choice2() {
        int studentID;
        Student student;
        System.out.println("Enter a student ID: ");
        studentID = scanner.nextInt();
        student = getStudent(studentID);
        if(student == null)
            System.out.println("No student with that ID.");
        else
            System.out.println(student.toString());
    }
    public static void choice3() {
        int studentID;
        Student student;
        System.out.println("Enter a student ID: ");
        studentID = scanner.nextInt();
        student = getStudent(studentID);
        if(student == null)
            System.out.println("No student with that ID.");
        else {
            String newPhone;
            System.out.println("Enter the new telephone number: ");
            newPhone = scanner.next();
            student.setPhone(newPhone);
        }
    }
    public static void choice4() {
        System.out.println("Student List:");
        for(int i = 0; i < noStudents; i++)
            System.out.println(students[i].toString());
        System.out.println("\n");
    }
    public static void main(String[] args) {
        int choice;

        students = new Student[100];
        noStudents = 0;
        scanner = new Scanner(System.in);

        choice = menu();
        while(choice != 5) {
            switch(choice) {
                case 1: choice1(); break;
                case 2: choice2(); break;
                case 3: choice3(); break;
                case 4: choice4(); break;
                default: break;
            }
            choice = menu();
        };
    }
    public static Student getStudent(int studentID) {
        for(int i = 0; i < noStudents; i++) {
            if(students[i].getID() == studentID)
                return students[i];
        }
        return null;
    }
}
