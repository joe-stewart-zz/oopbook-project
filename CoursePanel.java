import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * CoursePanel
 *
 */

public class CoursePanel extends JPanel implements ActionListener {
    private StudentApplication studentApplication;
    private University university;
    private JTextField courseCodeField;
    private JTextField courseTitleField;
    private JTextField courseCreditsField;
    private JTextField courseMaxStudentsField;
    private JTextField studentIDField;
    private JTextArea studentsField;

    /**
     * Method CoursePanel
     *
     *
     * @param studentApplication
     * @param university
     *
     */

    public CoursePanel(StudentApplication studentApplication, University university) {

        this.studentApplication = studentApplication;
        this.university = university;

        setLayout(new GridBagLayout());

        courseCodeField = new JTextField();
        courseTitleField = new JTextField();
        courseCreditsField = new JTextField();
        courseMaxStudentsField = new JTextField();
        studentIDField = new JTextField();

        studentsField = new JTextArea(14, 20);
        studentsField.setEditable(false);
        JScrollPane scroller = new JScrollPane();
        scroller.getViewport().add(studentsField);

        constrain(new JLabel("Course Code:"), 0, 0, 2, GridBagConstraints.HORIZONTAL);
        constrain(courseCodeField, 2, 0, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Title:"), 0, 1, 2, GridBagConstraints.HORIZONTAL);
        constrain(courseTitleField, 2, 1, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Credits:"), 0, 2, 2, GridBagConstraints.HORIZONTAL);
        constrain(courseCreditsField, 2, 2, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Enrollment Limit:"), 0, 3, 2, GridBagConstraints.HORIZONTAL);
        constrain(courseMaxStudentsField, 2, 3, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Student ID:"), 0, 4, 2, GridBagConstraints.HORIZONTAL);
        constrain(studentIDField, 2, 4, 5, GridBagConstraints.HORIZONTAL);

        constrain(new JLabel("Registered Students:"), 0, 5, 2, GridBagConstraints.HORIZONTAL);
        constrain(scroller, 2, 5, 5, GridBagConstraints.HORIZONTAL);

        constrain(createJButton("Clear"), 0, 20, 1, GridBagConstraints.BOTH);
        constrain(createJButton("Query"), 1, 20, 1, GridBagConstraints.BOTH);
        constrain(createJButton("New"), 2, 20, 1, GridBagConstraints.BOTH);
        constrain(createJButton("Update"), 3, 20, 1, GridBagConstraints.BOTH);
        constrain(createJButton("Register"), 4, 20, 1, GridBagConstraints.BOTH);
        constrain(createJButton("De-register"), 5, 20, 1, GridBagConstraints.BOTH);

        // create four Course objects

        university.addCourse("COMP1100", "Computer Programming I", 6, 20);
        university.addCourse("COMP3100", "Operating Systems", 4, 2);
        university.addCourse("COMP2500", "Object-Oriented Programming", 4, 3);
        university.addCourse("COMP1200", "Computer Programming II", 6, 4);
    }

    /**
     * Method constrain
     *
     *
     * @param component
     * @param grid_x
     * @param grid_y
     * @param grid_width
     * @param fill
     *
     */

    private void constrain(Component component, int grid_x, int grid_y, int grid_width, int fill) {
        GridBagConstraints gbConstraints = new GridBagConstraints();
        gbConstraints.gridx = grid_x;
        gbConstraints.gridy = grid_y;
        gbConstraints.gridwidth = grid_width;
        gbConstraints.fill = fill;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.weightx = 0.0;
        gbConstraints.weighty = 0.0;
        add(component, gbConstraints);
    }

    /**
     * Method createJButton
     *
     *
     * @param name
     *
     */

    private JButton createJButton(String name) {
        JButton button = new JButton(name);
        button.addActionListener(this);
        return button;
    }

    /**
     * Method actionPerformed
     *
     *
     * @param event
     *
     */

    public void actionPerformed(ActionEvent event) {
        try {
            String command = event.getActionCommand();
            studentApplication.setStatus(command + " operation selected.");
            if (command.equals("Clear"))
                clear();
            else if(command.equals("Query"))
                query();
            else if (command.equals("New"))
                addCourse();
            else if (command.equals("Update"))
                updateCourse();
            else if (command.equals("Register"))
                register();
            else if(command.equals("De-register"))
                deregister();
        }
        catch (Error error) {
            studentApplication.setStatus("Error occurred: " + error.getMessage());
        }
        catch (Exception exception) {
            studentApplication.setStatus("Exception occurred: " + exception.getMessage());
        }
    }

    /**
     * Method clear
     *
     */

    private void clear() {
        courseCodeField.setText("");
        courseTitleField.setText("");
        courseCreditsField.setText("");
        courseMaxStudentsField.setText("");
        studentIDField.setText("");
        studentsField.setText("");
    }

    /**
     * Method query
     *
     */

    private void query() {
        try {
            String courseCode = courseCodeField.getText();
            if (courseCode.length() > 0) {
                Course course = university.getCourse(courseCode);
                if (course == null)
                    studentApplication.setStatus("No course with this code exists.");
                else {
                    studentApplication.setStatus("Course found.");
                    setCourse(course);
                }
            }
            else
                studentApplication.setStatus("To query, enter a Course Code.");
        }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("getCourse() not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("University.class not found.");
        }
    }

    /**
     * Method setCourse
     *
     */

    private void setCourse(Course course) {
        try {
            clear();
            courseCodeField.setText(course.getCode());
            courseTitleField.setText(course.getTitle());
            courseCreditsField.setText(course.getNumCredits() + "");
            courseMaxStudentsField.setText(course.getMaxStudents() + "");
            studentsField.setText("");

            ArrayList studentsList = university.getStudents(course.getCode());

            String output = "The following " + studentsList.size() + " students are registered:\n\n";

            for(int i=0; i<studentsList.size(); i++) {
                Student s = (Student) studentsList.get(i);
                output = output + s.getID() + ", " + s.getName() + "\n";
            }

            studentsField.setText(output);
        }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("Course methods not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("Course.class not found.");
        }
    }

    /**
     * Method addCourse
     *
     */

    private void addCourse() {

        Course course;

        try {
            String courseCode = courseCodeField.getText();

            if (courseCode.length() > 0) {
                course = university.getCourse(courseCode);
                if (course != null)
                    studentApplication.setStatus("Course already exists.");
                else {
                    String title = courseTitleField.getText();
                    int numCredits = Integer.parseInt(courseCreditsField.getText());
                    int maxStudents = Integer.parseInt(courseMaxStudentsField.getText());
                    course = university.addCourse(courseCode, title, numCredits, maxStudents);
                    if (course != null) {
                        setCourse(course);
                        studentApplication.setStatus("Course successfully added.");
                    }
                    else
                        studentApplication.setStatus("Course not added.");
                }
            }
            else
                studentApplication.setStatus("Enter a valid course code.");
        }
        catch (NoSuchMethodError error){
            studentApplication.setStatus("addCourse() not implemented.");
        }
        catch (NoClassDefFoundError error){
            studentApplication.setStatus("University.class not found.");
        }
    }

    /**
     * Method update
     *
     */

    private void updateCourse() {

        Course course;

        try {
            String courseCode = courseCodeField.getText();

            if (courseCode.length() > 0) {
                course = university.getCourse(courseCode);
                if (course == null)
                    studentApplication.setStatus("No course with this code exists.");
                else {
                    String title = courseTitleField.getText();
                    int numCredits = Integer.parseInt(courseCreditsField.getText());
                    int maxStudents = Integer.parseInt(courseMaxStudentsField.getText());

                    course = university.updateCourse(courseCode, title, numCredits, maxStudents);

                    if (course != null) {
                        setCourse(course);
                        studentApplication.setStatus("Course successfully updated.");
                    }
                    else
                        studentApplication.setStatus("Course not updated.");
                }
            }
            else
                studentApplication.setStatus("Enter a valid course code.");
        }
        catch (NoSuchMethodError error){
            studentApplication.setStatus("updateCourse() not implemented.");
        }
        catch (NoClassDefFoundError error){
            studentApplication.setStatus("University.class not found.");
        }
    }

    /**
     * Method register
     *
     */

    private void register() {
        try {
            String id = studentIDField.getText();
            String courseCode = courseCodeField.getText();

            if (id.length() == 0 || courseCode.length() == 0)
                studentApplication.setStatus("Please enter a valid student ID and course code.");
            else {
                boolean success;

                success = university.registerStudent(Long.parseLong(id), courseCode);
                if (success)
                    studentApplication.setStatus("Student registered for course.");
                else
                    studentApplication.setStatus("Registration failed.");
            }
        }
        catch (NoSuchMethodError error){
            studentApplication.setStatus("University methods not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("University.class not found.");
        }
        catch (TooManyCreditsException tmce) {
            studentApplication.setStatus(tmce.getMessage());
        }
        catch (CourseFullException cfe) {
            studentApplication.setStatus(cfe.getMessage());
        }
    }

    /**
     * Method deregister
     *
     */

    private void deregister() {
        try {
            String id = studentIDField.getText();
            String courseCode = courseCodeField.getText();
            if (id.length() == 0 || courseCode.length() == 0)
                studentApplication.setStatus("Please enter a valid student ID and course code.");
            else {
                boolean success;
                success = university.deRegisterStudent(Long.parseLong(id), courseCode);
                if (success) {
                    studentApplication.setStatus("Student de-registered from course.");
                }
                else
                    studentApplication.setStatus("De-registration failed.");
            }
        }
        catch (NoSuchMethodError error){
            studentApplication.setStatus("deregister()/getCourse() methods not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("University.class not found. ");
        }
    }
}
