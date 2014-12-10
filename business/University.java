package business;

import java.util.*;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class University {
    private static final int MAX_CREDITS = 20;

    private static University university = null;

    private String name;
    private HashMap<Long, Student> students;
    private HashMap<String, Course> courses;
    private HashMap<Student, ArrayList<Registration>> registrations1;
    private HashMap<Course, ArrayList<Registration>> registrations2;

    private University(String name) {
        this.name = name;
        students = new HashMap<Long, Student>();
        courses = new HashMap<String, Course>();
        registrations1 = new HashMap<Student, ArrayList<Registration>>();
        registrations2 = new HashMap<Course, ArrayList<Registration>>();
    }

    public static University getInstance() {
        if(university == null)
            university = new University("The University of Computing");
        return university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course addCourse(String code, String title, int noCredits, int maxStudents) {
        Course course = getCourse(code);
        if(course == null) {
            course = new Course(code, title, noCredits, maxStudents);
            courses.put(code, course);
            return course;
        }
        else
            return null;
    }

    public Course updateCourse(String code, String title, int noCredits, int maxStudents) {
        Course course = getCourse(code);
        if(course != null) {
            course.setTitle(title);
            course.setNumCredits(noCredits);
            course.setMaxStudents(maxStudents);
            return course;
        }
        else
            return null;
    }

    public Student addStudent(long ID, String name, String address, String phone, String email) {
        Student student = getStudent(ID);
        if(student != null)
            return null;
        Student newStudent = new Student(ID, name, address, phone, email);
        students.put(new Long(ID), newStudent);
        return newStudent;
    }

    public Student updateStudent(long ID, String name, String address, String phone, String email) {
        Student student = getStudent(ID);
        if(student == null)
            return null;
        student.setName(name);
        student.setAddress(address);
        student.setPhone(phone);
        student.setEmail(email);
        return student;
    }

    public UndergraduateStudent addUndergraduateStudent(long ID, String major, String minor) {
        Student student = getStudent(ID);
        if(student == null)
            return null;
        UndergraduateStudent ugStudent = new UndergraduateStudent(student, "UndergraduateStudent", major, minor);
        student.addRole(ugStudent);
        return ugStudent;
    }

    public UndergraduateStudent updateUndergraduateStudent(long ID, String major, String minor) throws NoSuchRoleException {
        Student student = getStudent(ID);
        if(student == null)
            return null;
        UndergraduateStudent ugStudent = (UndergraduateStudent) student.getRole("UndergraduateStudent");
        if(ugStudent == null)
            throw new NoSuchRoleException("UndergraduateStudent role does not exist.");
        else {
            ugStudent.setMajor(major);
            ugStudent.setMinor(minor);
        }
        return  ugStudent;
    }

    public PostgraduateStudent addPostgraduateStudent(long ID, String thesisTitle, String supervisor) {
        Student student = getStudent(ID);
        if(student == null)
            return null;
        PostgraduateStudent pgStudent = new PostgraduateStudent(student, "PostgraduateStudent", thesisTitle, supervisor);
        student.addRole(pgStudent);
        return pgStudent;
    }

    public PostgraduateStudent updatePostgraduateStudent(long ID, String thesisTitle, String supervisor) throws NoSuchRoleException {
        Student student = getStudent(ID);
        if(student == null)
            return null;
        PostgraduateStudent pgStudent = (PostgraduateStudent) student.getRole("PostgraduateStudent");
        if(pgStudent == null)
            throw new NoSuchRoleException("PostgraduateStudent Role does not exist");
        else {
                pgStudent.setThesisTitle(thesisTitle);
                pgStudent.setSupervisor(supervisor);
        }
        return pgStudent;
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public Student getStudent(long ID) {
        return students.get(ID);
    }

    public Collection<Course> getCourses(long studentID) {
        Student student = students.get(studentID);
        TreeSet<Course> regCourses = null;
        if(student != null) {
            Collection<Registration> registrations = registrations1.get(student);
            regCourses = new TreeSet<Course>();
            if(registrations == null)
                return regCourses;
            Iterator<Registration> i = registrations.iterator();
            while(i.hasNext()) {
                Registration r = i.next();
                regCourses.add(r.getCourse());
            }
            return regCourses;
        }
        return null;
    }

    public Collection<Student> getStudents(String courseCode) {
        Course course = courses.get(courseCode);
        TreeSet<Student> regStudents = null;
        if(course != null) {
            Collection<Registration> registrations = registrations2.get(course);
            regStudents = new TreeSet<Student>(new StudentNameComparator());
            if(registrations == null)
                return regStudents;
            Iterator<Registration> i = registrations.iterator();
            while(i.hasNext()) {
                Registration r = i.next();
                regStudents.add(r.getStudent());
            }
            return regStudents;
        }
        return null;
    }

    private Registration getRegistration(Collection<Registration> registrations, Course course) {
        Iterator<Registration> i = registrations.iterator();
        while(i.hasNext()) {
            Registration r = i.next();
            if(r.getCourse().equals(course))
                return r;
        }
        return null;
    }

    private int getCredits(Collection<Course> courses) {
        Iterator<Course> i = courses.iterator();
        int numCredits = 0;
        while(i.hasNext()) {
            Course c = i.next();
            numCredits = numCredits + c.getNumCredits();
        }
        return numCredits;
    }

    public boolean registerStudent(long studentID, String courseCode) throws TooManyCreditsException, CourseFullException {
        Collection<Course> studentCourses;
        Collection<Student> courseStudents;
        Student student = getStudent(studentID);
        Course course = getCourse(courseCode);
        if(student == null || course == null)
            return false;
        studentCourses = getCourses(studentID);
        courseStudents = getStudents(courseCode);
        if(getCredits(studentCourses) + course.getNumCredits() > MAX_CREDITS)
            throw new TooManyCreditsException();
        if(courseStudents.size() == course.getMaxStudents())
            throw new CourseFullException();
        Registration registration = new Registration(student, course);
        Collection<Registration> existingListStudent, existingListCourse;
        existingListStudent = registrations1.get(student);
        if(existingListStudent == null) {
            ArrayList<Registration> newListStudent = new ArrayList<Registration>();
            newListStudent.add(registration);
            registrations1.put(student, newListStudent);
        } else {
            existingListStudent.add(registration);
        }
        existingListCourse = registrations2.get(course);
        if(existingListCourse == null) {
            ArrayList<Registration> newListCourse = new ArrayList<Registration>();
            newListCourse.add(registration);
            registrations2.put(course, newListCourse);
        } else {
            existingListCourse.add(registration);
        }
        return true;
    }

    public boolean deRegisterStudent(long studentID, String courseCode) {
        Registration registration;
        Student student = getStudent(studentID);
        Course course = getCourse(courseCode);
        if(student == null || course == null)
            return false;
        Collection<Registration> registrationsStudent, registrationsCourse;
        registrationsStudent = registrations1.get(student);
        registrationsCourse = registrations2.get(course);
        if(registrationsStudent == null)
            return false;
        registration = getRegistration(registrationsStudent, course);
        if(registration != null) {
            registrationsStudent.remove(registration);
            registrationsCourse.remove(registration);
            return true;
        }
        return false;
    }

    public String getStudents(Comparator<Student> comparator) {
        TreeSet<Student> sorted = new TreeSet<Student>(comparator);
        Collection<Student> s = students.values();
        sorted.addAll(s);
        String output = "";
        Iterator<Student> i = sorted.iterator();
        while(i.hasNext()) {
            String type;
            Student student = i.next();
            output = output + student.getID() + ", " + student.getName() + ", " + student.getRoleNames() + "\n";
        }
        output = "There are " + sorted.size() + " students: " + "\n\n" + output;
        return output;
    }

    public String getUndergraduateStudents(Comparator<Student> comparator) {
        TreeSet<Student> sorted = new TreeSet<Student>(comparator);
        Collection<Student> s = students.values();
        sorted.addAll(s);
        String output = "";
        int count = 0;
        Iterator<Student> i = sorted.iterator();
        while(i.hasNext()) {
            Student student = i.next();
            String roleNames = student.getRoleNames();
            int index = roleNames.indexOf("UndergraduateStudent");
            if(index >= 0) {
                output = output + student.getID() + ", " + student.getName() + "\n";
                count++;
            }
        }
        output = "There are " + count + " undergraduate students: " + "\n\n" + output;
        return output;
    }

    public String getPostgraduateStudents(Comparator<Student> comparator) {
        TreeSet<Student> sorted = new TreeSet<Student>(comparator);
        Collection<Student> s = students.values();
        sorted.addAll(s);
        String output = "";
        int count = 0;
        Iterator<Student> i = sorted.iterator();
        while(i.hasNext()) {
            Student student = i.next();
            String roleNames = student.getRoleNames();
            int index = roleNames.indexOf("PostgraduateStudent");
            if(index >= 0) {
                output = output + student.getID() + ", " + student.getName() + "\n";
                count++;
            }
        }
        output = "There are " + count + " postgraduate students: " + "\n\n" + output;
        return output;
    }

    public String getCourses() {
        Collection<Course> c = courses.values();
        TreeSet<Course> sorted = new TreeSet<Course>(c);
        String output = "";
        Iterator<Course> i = sorted.iterator();
        while(i.hasNext()) {
            Course course = i.next();
            output = output + course.getCode() + ", " + course.getTitle() + ", " + course.getNumCredits() + " credits\n";
        }
        output = "There are " + sorted.size() + " courses: " + "\n\n" + output;
        return output;
    }

    public void read() throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("university.ser");
            ois = new ObjectInputStream(fis);
            students = (HashMap<Long, Student>) ois.readObject();
            courses = (HashMap<String, Course>) ois.readObject();
            registrations1 = (HashMap<Student, ArrayList<Registration>>) ois.readObject();
            registrations2 = (HashMap<Course, ArrayList<Registration>>) ois.readObject();
        } finally {
            if(ois != null)
                ois.close();
            if(fis != null)
                fis.close();
        }
    }

    public void save() throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("university.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(students);
            oos.writeObject(courses);
            oos.writeObject(registrations1);
            oos.writeObject(registrations2);
        } finally {
            if(oos != null)
                oos.close();
            if(fos != null)
                fos.close();
        }
    }

    public static void main(String[] args) {
    }
}
