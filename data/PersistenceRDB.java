package data;

import business.*;
import static data.Constants.*;

import java.util.*;
import java.io.*;
import java.sql.*;

public class PersistenceRDB implements Persistence {
    private University university;
    private Connection connection;

    String insertRow = "";

    public PersistenceRDB(University university) {
        this.university = university;
        connection = null;
    }

    public void read() throws Exception {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(CONNECTION);

        readCourses();
        readStudents();
        readUndergraduateStudents();
        readPostgraduateStudents();
        readRegistrations();
    }

    public void save() throws Exception {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(CONNECTION);

        saveCourses();
        saveStudents();
        saveUndergraduateStudents();
        savePostgraduateStudents();
        saveRegistrations();
    }

    public void readCourses() throws ClassNotFoundException, IOException, SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * From Course");
        while(rs.next()) {
            String code = rs.getString("code");
            String title = rs.getString("title");
            int numCredits = rs.getInt("numCredits");
            int maxStudents = rs.getInt("maxStudents");
            university.addCourse(code, title, numCredits, maxStudents);
        }
        stmt.close();
    }

    public void readStudents() throws ClassNotFoundException, IOException, SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * From Student");
        while(rs.next()) {
            long studentID = rs.getLong("studentID");
            String name = rs.getString("name");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            university.addStudent(studentID, name, address, phone, email);
        }
        stmt.close();
    }

    public void readUndergraduateStudents() throws ClassNotFoundException, IOException, SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * From UGStudent");
        while(rs.next()) {
            long studentID = rs.getLong("studentID");
            String major = rs.getString("major");
            String minor = rs.getString("minor");
            university.addUndergraduateStudent(studentID, major, minor);
        }
        stmt.close();
    }

    public void readPostgraduateStudents() throws ClassNotFoundException, IOException, SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * From PGStudent");
        while(rs.next()) {
            long studentID = rs.getLong("studentID");
            String thesisTitle = rs.getString("thesisTitle");
            String supervisor = rs.getString("supervisor");
            university.addPostgraduateStudent(studentID, thesisTitle, supervisor);
        }
        stmt.close();
    }

    public void readRegistrations() throws ClassNotFoundException, IOException, SQLException, TooManyCreditsException, CourseFullException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * From Registration");
        while(rs.next()) {
            long studentID = rs.getLong("studentID");
            String courseCode = rs.getString("courseCode");
            university.registerStudent(studentID, courseCode);
        }
        stmt.close();
    }

    public void saveCourses() throws IOException, SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("Delete From Course");
        Iterator<Course> i = university.getCourses().iterator();
        while(i.hasNext()) {
            Course course = i.next();
            String insertRow = "INSERT INTO Course VALUES ('" +
                                course.getCode() + "', '" +
                                course.getTitle() + "', " +
                                course.getNumCredits() + ", " +
                                course.getMaxStudents() + ")";
            stmt.execute(insertRow);
        }
        stmt.close();
    }

    public void saveStudents() throws IOException, SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("Delete From Student");
        Iterator<Student> i = university.getStudents().iterator();
        while(i.hasNext()) {
            Student student = i.next();
            String insertRow = "INSERT INTO Student VALUES (" +
                                student.getID() + ", '" +
                                student.getName() + "', '" +
                                student.getAddress() + "', '" +
                                student.getPhone() + "', '" +
                                student.getEmail() + "')";
            stmt.execute(insertRow);
        }
        stmt.close();
    }

    public void saveUndergraduateStudents() throws IOException, SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("Delete From UGStudent");
        Iterator<UndergraduateStudent> i = university.getUndergraduateStudents().iterator();
        while(i.hasNext()) {
            UndergraduateStudent ugStudent = i.next();
            String insertRow = "INSERT INTO UGStudent VALUES (" +
                                ugStudent.getStudent().getID() + ", '" +
                                ugStudent.getMajor() + "', '" +
                                ugStudent.getMinor() + "')";
            stmt.execute(insertRow);
        }
        stmt.close();
    }

    public void savePostgraduateStudents() throws IOException, SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("Delete From PGStudent");
        Iterator<PostgraduateStudent> i = university.getPostgraduateStudents().iterator();
        while(i.hasNext()) {
            PostgraduateStudent pgStudent = i.next();
            String insertRow = "INSERT INTO PGStudent VALUES (" +
                                pgStudent.getStudent().getID() + ", '" +
                                pgStudent.getThesisTitle() + "', '" +
                                pgStudent.getSupervisor() + "')";
            stmt.execute(insertRow);
        }
        stmt.close();
    }

    public void saveRegistrations() throws IOException, SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("Delete From Registration");
        Iterator<ArrayList<Registration>> i = university.getRegistrations1().iterator();
        while(i.hasNext()) {
            ArrayList<Registration> registrations = i.next();
            for(int j = 0; j < registrations.size(); j++) {
                Registration registration = registrations.get(j);
                String insertRow = "INSERT INTO Registration VALUES (" +
                                    registration.getStudent().getID() + ", '" +
                                    registration.getCourse().getCode() + "')";
                stmt.execute(insertRow);
            }
        }
        stmt.close();
    }
}
