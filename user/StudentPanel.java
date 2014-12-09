package user;

import business.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;



/**
 * StudentPanel
 *
 */

public class StudentPanel 
        extends JPanel 
        implements ActionListener {
    
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
    ButtonGroup buttonGroup = new ButtonGroup();


    
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


/*      There is no need for the following radio buttons from Version 1:

        undergraduateField = new JRadioButton("Undergraduate Student", true);
        postgraduateField = new JRadioButton("Postgraduate Student", false);
*/

        undergraduateField = new JRadioButton("UG Role", false);
        postgraduateField = new JRadioButton("PG Role", false);

        buttonGroup = new ButtonGroup();
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

        constrain(new JLabel("Course Code:"), 0, 5, 2, GridBagConstraints.HORIZONTAL);
        constrain(courseCodeField, 2, 5, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Registered Courses:"), 0, 6, 2, GridBagConstraints.HORIZONTAL);
        constrain(scroller, 2, 6, 5, GridBagConstraints.HORIZONTAL);        

        constrain(createJButton("Clear"), 0, 12, 1, GridBagConstraints.BOTH);
        constrain(createJButton("Query"), 1, 12, 1, GridBagConstraints.BOTH);
        constrain(createJButton("New"), 2, 12, 1, GridBagConstraints.BOTH);
        constrain(createJButton("Update"), 3, 12, 1, GridBagConstraints.BOTH);
        constrain(createJButton("Register"), 4, 12, 1, GridBagConstraints.BOTH);
        constrain(createJButton("De-register"), 5, 12, 1, GridBagConstraints.BOTH);

        constrain(new JLabel("Major:"), 0, 13, 2, GridBagConstraints.HORIZONTAL);
        constrain(majorField, 2, 13, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Minor:"), 0, 14, 2, GridBagConstraints.HORIZONTAL);
        constrain(minorField, 2, 14, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Thesis Title:"), 0, 15, 2, GridBagConstraints.HORIZONTAL);
        constrain(thesisField, 2, 15, 5, GridBagConstraints.HORIZONTAL);
        constrain(new JLabel("Supervisor:"), 0, 16, 2, GridBagConstraints.HORIZONTAL);
        constrain(supervisorField, 2, 16, 5, GridBagConstraints.HORIZONTAL);

        constrain(undergraduateField, 0, 17, 1, GridBagConstraints.HORIZONTAL);
        constrain(postgraduateField, 1, 17, 1, GridBagConstraints.HORIZONTAL);
        constrain(createJButton("Display Role"), 2, 17, 2, GridBagConstraints.BOTH);
        constrain(createJButton("Add Role"), 4, 17, 1, GridBagConstraints.BOTH);
        constrain(createJButton("Update Role"), 5, 17, 1, GridBagConstraints.BOTH);

        university.addStudent(50, "Anthony Roberts", "#10 Charles Avenue, Gasparillo", "868-1358", "anthony.roberts@gmail.com");
        university.addStudent(20, "Mary Doe", "Couva", "456-7890", "marydoe2012@hotmail.com");
        university.addStudent(10, "John Doe", "#43 Main Street, Port of Spain", "123-4567", "john.doe@gmail.com");
        university.addStudent(40, "Lisa Smith", "Cedros", "328-6543", "lisasmith204@hotmail.com");
        university.addStudent(30, "Peter Smith", "San Fernando", "321-8765", "joseph.smith@mysta.uwi.edu");

        university.addUndergraduateStudent(50, "B.Sc. Economics", "Mathematics");
        university.addUndergraduateStudent(20, "B.Sc. Management Studies", "History");
        university.addUndergraduateStudent(10, "B.Sc. Computer Science", "Electronics");
        university.addPostgraduateStudent(40, "Ph.D. Computer Science", "M. Hosein");
        university.addPostgraduateStudent(30, "M.Phil. Computer Science", "P. Mohan");
        university.addPostgraduateStudent(50, "Ph.D. Mathematics", "Prof. E.J. Farrell");

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
            else 
            if (command.equals("Query")) 
                query();
            else 
            if (command.equals("New")) 
                addStudent();
            else 
            if (command.equals("Update")) 
                updateStudent();
            else 
            if (command.equals("Register")) 
                register();
            else 
            if (command.equals("De-register")) 
                deregister();
            if (command.equals("Display Role")) 
                displayRole();
            if (command.equals("Add Role")) 
                addRole();
            if (command.equals("Update Role")) 
                updateRole();

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

        clearStudentFields();
        clearStudentRoleFields();
        buttonGroup.clearSelection();
    }
   


    /**
     * Method clearStudentFields
     *
     */

    private void clearStudentFields() {
        studentIDField.setText("");
        studentNameField.setText("");
        studentAddressField.setText("");
        studentPhoneField.setText("");
        studentEmailField.setText("");
        courseCodeField.setText("");
        coursesField.setText("");
    }



    /**
     * Method clearStudentRoleFields
     *
     */

    private void clearStudentRoleFields() {
        majorField.setText("");
        minorField.setText("");
        thesisField.setText("");
        supervisorField.setText("");

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

/*          
            There is no need to determine if a Student is an UndergraduateStudent or
            a PostgraduateStudent. These are now implemented as roles.

                if (student instanceof PostgraduateStudent) {
                postgraduateField.setSelected(true);
                thesisField.setText(((PostgraduateStudent) student).getThesisTitle());
                supervisorField.setText(((PostgraduateStudent) student).getSupervisor());
                }
                else 
            if(student instanceof UndergraduateStudent) {
                undergraduateField.setSelected(true);
                majorField.setText(((UndergraduateStudent) student).getMajor());
                minorField.setText(((UndergraduateStudent) student).getMinor());
                }
*/
        
            Collection<Course> coursesList = university.getCourses(student.getID());

            String output = "Student is registered for " + coursesList.size() + " course/s:\n\n";

            Iterator<Course> i = coursesList.iterator();
            while (i.hasNext()) {
                Course c = i.next();
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
                    student = university.addStudent(
                                studentID,
                                studentNameField.getText(),
                                studentAddressField.getText(), 
                                studentPhoneField.getText(),
                                    studentEmailField.getText());

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
     * Method updateStudent
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
                    student = university.updateStudent(
                            studentID,
                            studentNameField.getText(),
                            studentAddressField.getText(), 
                            studentPhoneField.getText(),
                                studentEmailField.getText());
                    
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
                    studentApplication.setStatus("Please enter a valid student ID and course code.");
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



    /**
     * Method displayRole
     *
     */

    private void displayRole() {

        try {
            String id = studentIDField.getText();
            if (id.length() > 0) {
                Student student = university.getStudent(Long.parseLong(id));

                if (student == null) 
                    studentApplication.setStatus("No student with this ID exists.");
                else {
                    // setStudent(student);
                    clearStudentRoleFields();
                        if (undergraduateField.isSelected()) {
                        UndergraduateStudent ugStudent = (UndergraduateStudent) 
                            student.getRole("UndergraduateStudent");

                        if (ugStudent != null) {
                            majorField.setText(ugStudent.getMajor());
                            minorField.setText(ugStudent.getMinor());
                            studentApplication.setStatus("Undergraduate role displayed.");
                        }
                        else
                            studentApplication.setStatus("Student does not have Undergraduate role.");
                    }
                    else
                        if (postgraduateField.isSelected()) {
                        PostgraduateStudent pgStudent = (PostgraduateStudent) 
                            student.getRole("PostgraduateStudent");
                        
                        if (pgStudent != null) {
                            thesisField.setText(pgStudent.getThesisTitle());
                            supervisorField.setText(pgStudent.getSupervisor());
                            studentApplication.setStatus("Postgraduate role displayed.");
                        }
                        else
                            studentApplication.setStatus("Student does not have Postgraduate role.");

                    }
                    else 
                        studentApplication.setStatus("Please select a role first.");
                }                   
                }
            else
                studentApplication.setStatus("To display a role, enter a Student ID.");
            }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("getStudent() not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("University.class not found.");
        }
        }



    /**
     * Method addRole
     *
     */

    private void addRole() {

        long studentID = Long.parseLong(studentIDField.getText());

            if (undergraduateField.isSelected()) {

            UndergraduateStudent ugStudent;

            ugStudent = university.addUndergraduateStudent(
                                studentID,
                                majorField.getText(), 
                                minorField.getText());

            if (ugStudent == null)
                studentApplication.setStatus("There is no student with this ID.");
            else
                studentApplication.setStatus("Undergraduate role successfully added.");

        }
        else 
        if (postgraduateField.isSelected()) {

            PostgraduateStudent pgStudent;

            
            pgStudent = university.addPostgraduateStudent(
                                studentID,
                                thesisField.getText(), 
                                supervisorField.getText());

            if (pgStudent == null)
                studentApplication.setStatus("There is no student with this ID.");
            else
                studentApplication.setStatus("Postgraduate role successfully added.");
        }
        else

            studentApplication.setStatus("Please select a role first.");


    }



    /**
     * Method updateRole
     *
     */

    private void updateRole() {

        long studentID = Long.parseLong(studentIDField.getText());

        try {
                if (undergraduateField.isSelected()) {

                UndergraduateStudent ugStudent;

                ugStudent = university.updateUndergraduateStudent(
                                studentID,
                                majorField.getText(), 
                                minorField.getText());

                if (ugStudent == null)
                    studentApplication.setStatus("There is no student with this ID.");
                else
                    studentApplication.setStatus("Undergraduate role successfully updated.");

            }
            else 
            if (postgraduateField.isSelected()) {

                PostgraduateStudent pgStudent;

                pgStudent = university.updatePostgraduateStudent(
                                studentID,
                                thesisField.getText(), 
                                supervisorField.getText());

                if (pgStudent == null)
                    studentApplication.setStatus("There is no student with this ID.");
                else
                    studentApplication.setStatus("Postgraduate role successfully updated.");
            }
            else
                studentApplication.setStatus("Please select a role first.");
        }
        catch (NoSuchRoleException nsre) {
            studentApplication.setStatus("Student does not have this role.");
        }

    }

}
