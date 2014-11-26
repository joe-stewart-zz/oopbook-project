import java.util.*;

public class StudentApplication {
    private static University university;
    private static Scanner scanner;

    public static int menu() {
        int choice;

        System.out.println("\n\n");
        System.out.println("1. Add new course");
        System.out.println("2. Add new student");
        System.out.println("3. Display course info");
        System.out.println("4. Display student info");
        System.out.println("5. Register student");
        System.out.println("6. List all courses");
        System.out.println("7. List all students");
        System.out.println("8. List all courses for student");
        System.out.println("9. Exit");
        System.out.print("\n\nPlease make a selection: ");

        choice = scanner.nextInt();
        return choice;
    }

    public static void choice1() {
        String courseCode, title;
        int noCredits;
        System.out.println("Enter the course code, title and credits:");
        courseCode = scanner.next();
        title = scanner.next();
        noCredits = scanner.nextInt();
        university.addCourse(courseCode, title, noCredits);
    }

    public static void choice2() {
        String firstName, lastName, phone;
        System.out.println("Enter the student's first name, last name, and telephone number:");
        firstName = scanner.next();
        lastName = scanner.next();
        phone = scanner.next();
        university.addStudent(firstName, lastName, phone);
    }

    public static void choice3() {
        String courseCode;
        System.out.println("Enter the course code:");
        courseCode = scanner.next();
        Course course = university.getCourse(courseCode);
        if(course == null)
            System.out.println("No course with that code.");
        else
            System.out.println(course.toString());
    }

    public static void choice4() {
        int studentID;
        System.out.println("Enter a student ID:");
        studentID = scanner.nextInt();
        Student student = university.getStudent(studentID);
        if(student == null)
            System.out.println("No student with that ID.");
        else
            System.out.println(student.toString());
    }

    public static void choice5() {
        int studentID;
        String courseCode;
        int academicYear, semester;
        boolean result;

        System.out.println("Enter a student ID:");
        studentID = scanner.nextInt();
        System.out.println("Enter a course code:");
        courseCode = scanner.next();
        System.out.println("Enter the academic year and semester:");
        academicYear = scanner.nextInt();
        semester = scanner.nextInt();

        result = university.registerStudent(studentID, courseCode, academicYear, semester);
        if(result == true)
            System.out.println("Registration successful.");
        else
            System.out.println("Error registering student.");
    }

    public static void choice6() {
        System.out.println(university.getCourses());
    }

    public static void choice7() {
        System.out.println(university.getStudents());
    }

    public static void choice8() {
        int studentID;
        String result;
        System.out.println("Enter a student ID: ");
        studentID = scanner.nextInt();
        result = university.getCourses(studentID);
        if(result == null)
            System.out.println("No student with that ID.");
        else
            System.out.println(result);
    }

    public static void main(String[] args) {
        int choice;

        university = new University("University of Computing");
        scanner = new Scanner(System.in);

        choice = menu();
        while(choice != 9) {
            switch(choice) {
                case 1: choice1(); break;
                case 2: choice2(); break;
                case 3: choice3(); break;
                case 4: choice4(); break;
                case 5: choice5(); break;
                case 6: choice6(); break;
                case 7: choice7(); break;
                case 8: choice8(); break;
                default: break;
            }
            choice = menu();
        };
    }
}
