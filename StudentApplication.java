import java.util.*;

public class StudentApplication {
    private static University university;
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
        String firstName, lastName, phone;
        Student student;

        System.out.println("Enter the student's first name, last name, and telephone number: ");
        firstName = scanner.next();
        lastName = scanner.next();
        phone = scanner.next();

        student = university.addStudent(firstName, lastName, phone);
        System.out.println("StudentID: " + student.getID());
    }

    public static void choice2() {
        int studentID;
        Student student;
        System.out.println("Enter a student ID: ");
        studentID = scanner.nextInt();

        student = university.getStudent(studentID);
        if(student == null)
            System.out.println("No student with that ID.");
        else
            System.out.println(student.toString());
    }

    public static void choice3() {
        int studentID;
        String newPhone;
        boolean changed;

        System.out.println("Enter a student ID: ");
        studentID = scanner.nextInt();

        System.out.println("Enter new telephone number: ");
        newPhone = scanner.next();

        changed = university.changePhone(studentID, newPhone);
        if(!changed)
            System.out.println("No student with that ID.");
    }

    public static void choice4() {
        System.out.println(university.getStudents());
    }

    public static void main(String[] args) {
        int choice;

        university = new University();
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
}
