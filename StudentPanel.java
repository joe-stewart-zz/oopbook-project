import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * StudentPanel
 *
 */

public class StudentPanel extends JPanel implements ActionListener {
    private StudentApplication studentApplication;
    private University university;
    private JTextField studentIDField;
    private JTextField studentNameField;
    private JTextField studentAddressField;
    private JTextField studentPhoneField;
    private JTextField studentEmailField;

    private JTextField majorField;
    private JTextField minorField;

    private JTextField thesisField; 
    private JTextField supervisorField;
    private JTextField courseCodeField;

    private JTextArea coursesField;
    private JRadioButton undergraduateField;
    private JRadioButton postgraduateField;

    /**
     * Method StudentPanel
     *
     *
     * @param studentApplication
     * @param university
     *
     */

    public StudentPanel(StudentApplication studentApplication, University university) {
        this.studentApplication = studentApplication;
        this.university = university;

        setLayout(new GridBagLayout());

        studentIDField = new JTextField();
        studentNameField = new JTextField();
        studentAddressField = new JTextField();
        studentPhoneField = new JTextField();
        studentEmailField = new JTextField();

        majorField = new JTextField();
        minorField = new JTextField();

        thesisField = new JTextField();
        supervisorField = new JTextField();

        courseCodeField = new JTextField();
        coursesField = new JTextArea(6, 20);
        coursesField.setEditable(false);
        JScrollPane scroller = new JScrollPane();
        scroller.getViewport().add(coursesField);

        undergraduateField = new JRadioButton("Undergraduate Student", true);
        postgraduateField = new JRadioButton("Postgraduate Student", false);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(undergraduateField);
        buttonGroup.add(postgraduateField);

        constrain(new JLabel("ID:"), 0, 0, 2, GridBagConstraints.HORIZONTAL);
        constrain(studentIDField, 2, 0, 5,  GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Name:"), 0, 1, 2, GridBagConstraints.HORIZONTAL);
        constrain(studentNameField, 2, 1, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Address:"), 0, 2, 2, GridBagConstraints.HORIZONTAL);
        constrain(studentAddressField, 2, 2, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Phone:"), 0, 3, 2, GridBagConstraints.HORIZONTAL);
        constrain(studentPhoneField, 2, 3, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Email:"), 0, 4, 2, GridBagConstraints.HORIZONTAL);
        constrain(studentEmailField, 2, 4, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Major:"), 0, 5, 2, GridBagConstraints.HORIZONTAL);
        constrain(majorField, 2, 5, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Minor:"), 0, 6, 2, GridBagConstraints.HORIZONTAL);
        constrain(minorField, 2, 6, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Thesis Title:"), 0, 7, 2, GridBagConstraints.HORIZONTAL);
        constrain(thesisField, 2, 7, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Supervisor:"), 0, 8, 2, GridBagConstraints.HORIZONTAL);
        constrain(supervisorField, 2, 8, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Course Code:"), 0, 9, 2, GridBagConstraints.HORIZONTAL);
        constrain(courseCodeField, 2, 9, 5, GridBagConstraints.HORIZONTAL);
        constrain(undergraduateField, 2, 10, 2, GridBagConstraints.HORIZONTAL);
        constrain(postgraduateField, 4, 10, 2, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Registered Courses:"), 0, 11, 2, GridBagConstraints.HORIZONTAL);

        constrain(scroller, 2, 11, 5, GridBagConstraints.HORIZONTAL);

        constrain(createJButton("Clear"), 0, 12, 1, GridBagConstraints.BOTH);
        constrain(createJButton("Query"), 1, 12, 1, GridBagConstraints.BOTH);
        constrain(createJButton("New"), 2, 12, 1, GridBagConstraints.BOTH);
        constrain(createJButton("Update"), 3, 12, 1, GridBagConstraints.BOTH);
        constrain(createJButton("Register"), 4, 12, 1, GridBagConstraints.BOTH);
        constrain(createJButton("De-register"), 5, 12, 1, GridBagConstraints.BOTH);

        // create four Student objects

        university.addUndergraduateStudent(10, "John Doe", "#43 Main Street, Port of Spain", "123-4567", "john.doe@gmail.com", "B.Sc. Computer Science", "Electronics");
        university.addUndergraduateStudent(20, "Mary Doe", "Couva", "456-7890", "marydoe2012@hotmail.com", "B.Sc. Management Studies", "History");
        university.addPostgraduateStudent(30, "Joseph Smith", "San Fernando", "321-8765", "joseph.smith@mysta.uwi.edu", "M.Phil. Computer Science", "P. Mohan");
        university.addPostgraduateStudent(40, "Lisa Smith", "Cedros", "328-6543", "lisasmith204@hotmail.com", "Ph.D. Computer Science", "M. Hosein");
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
            else if (command.equals("Query"))
                query();
            else if (command.equals("New"))
                addStudent();
            else if (command.equals("Update"))
                updateStudent();
            else if (command.equals("Register"))
                register();
            else if (command.equals("De-register"))
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
        studentIDField.setText("");
        studentNameField.setText("");
        studentAddressField.setText("");
        studentPhoneField.setText("");
        studentEmailField.setText("");
        majorField.setText("");
        minorField.setText("");
        thesisField.setText("");
        supervisorField.setText("");
        courseCodeField.setText("");
        coursesField.setText("");
        undergraduateField.setSelected(true);
    }

    /**
     * Method query
     *
     */

    private void query() {
        try {
            String id = studentIDField.getText();
            if (id.length() > 0) {
                Student student = university.getStudent(Long.parseLong(id));
                if (student == null)
                    studentApplication.setStatus("No student with this ID exists.");
                else {
                    studentApplication.setStatus("Student found.");
                    setStudent(student);
                }
            }
            else
                studentApplication.setStatus("To query, enter a Student ID.");
        }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("getStudent()/qryStudent() not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("University.class not found.");
        }
    }

    /**
     * Method setStudent
     *
     */

    private void setStudent(Student student) {
        try {
            clear();
            studentIDField.setText(student.getID() + "");
            studentNameField.setText(student.getName());
            studentAddressField.setText(student.getAddress());
            studentPhoneField.setText(student.getPhone());
            studentEmailField.setText(student.getEmail());

            if (student instanceof PostgraduateStudent) {
                postgraduateField.setSelected(true);
                thesisField.setText(((PostgraduateStudent) student).getThesisTitle());
                supervisorField.setText(((PostgraduateStudent) student).getSupervisor());
            }
            else if(student instanceof UndergraduateStudent) {
                undergraduateField.setSelected(true);
                majorField.setText(((UndergraduateStudent) student).getMajor());
                minorField.setText(((UndergraduateStudent) student).getMinor());
            }

            ArrayList coursesList = university.getCourses(student.getID());

            String output = "Student is registered for " + coursesList.size() + " course/s:\n\n";

            for(int i=0; i<coursesList.size(); i++) {
                Course c = (Course) coursesList.get(i);
                output = output + c.getCode() + ", " + c.getTitle() + "\n";
            }

            coursesField.setText(output);
        }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("Student methods not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("Student.class not found.");
        }
    }

    /**
     * Method addStudent
     *
     */

    private void addStudent() {

        Student student;

        try {
            String id = studentIDField.getText();
            if (id.length() > 0) {
                long studentID = Long.parseLong(id);
                student = university.getStudent(studentID);
                    if (student != null)
                    studentApplication.setStatus("Student with this ID already exists.");
                    else {
                        if (postgraduateField.isSelected()) {
                            student = university.addPostgraduateStudent(
                                studentID,
                                studentNameField.getText(),
                                studentAddressField.getText(),
                                studentPhoneField.getText(),
                                studentEmailField.getText(),
                                thesisField.getText(),
                                supervisorField.getText());
                        }
                        else {
                            student = university.addUndergraduateStudent(
                                studentID,
                                studentNameField.getText(),
                                studentAddressField.getText(),
                                studentPhoneField.getText(),
                                studentEmailField.getText(),
                                majorField.getText(),
                                minorField.getText());
                        }

                        if (student != null) {
                            setStudent(student);
                            studentApplication.setStatus("Student successfully added.");
                        }
                        else
                            studentApplication.setStatus("Student not added.");
                }
            }
            else
                studentApplication.setStatus("Enter a valid student ID.");
        }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("addUndergraduateStudent()/addPostgraduateStudent() methods not implemented.");
        }
        catch (NoClassDefFoundError error){
            studentApplication.setStatus("University.class not found.");
        }
    }

    /**
     * Method update
     *
     */

    private void updateStudent() {

        Student student;

        try {
            String id = studentIDField.getText();
            if (id.length() > 0) {
                long studentID = Long.parseLong(id);
                student = university.getStudent(studentID);
                if (student == null)
                    studentApplication.setStatus("No student with this ID exists.");
                else {
                    if (student instanceof PostgraduateStudent) {
                        student = university.updatePostgraduateStudent(
                            studentID,
                            studentNameField.getText(),
                            studentAddressField.getText(),
                            studentPhoneField.getText(),
                            studentEmailField.getText(),
                            thesisField.getText(),
                            supervisorField.getText());
                    }
                    else {
                        student = university.updateUndergraduateStudent(
                            studentID,
                            studentNameField.getText(),
                            studentAddressField.getText(),
                            studentPhoneField.getText(),
                            studentEmailField.getText(),
                            majorField.getText(),
                            minorField.getText());
                    }
                    if (student != null) {
                        setStudent(student);
                        studentApplication.setStatus("Student successfully updated.");
                    }
                    else
                        studentApplication.setStatus("Student not updated.");
                }
            }
            else
                studentApplication.setStatus("Enter a valid student ID.");
        }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("updateUndergraduateStudent()/updatePostgraduateStudent() methods not implemented.");
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
