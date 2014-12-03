import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * ViewAllPanel
 *
 */

public class ViewAllPanel extends JPanel implements ActionListener {
    private StudentApplication studentApplication;
    private University university;
    private JLabel viewLabel;
    private JTextArea viewField;

    /**
     * Method ViewPanel
     *
     *
     * @param studentApplication
     * @param university
     *
     */

    public ViewAllPanel(StudentApplication studentApplication, University university) {

        this.studentApplication = studentApplication;
        this.university = university;

        setLayout(new GridBagLayout());

        viewLabel = new JLabel("List Information on Students and Courses");
        viewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        viewField = new JTextArea(15, 32);
        viewField.setEditable(false);
        JScrollPane scroller = new JScrollPane();
        scroller.getViewport().add(viewField);

        constrain(viewLabel, 0, 0, 4, GridBagConstraints.HORIZONTAL);
        constrain(scroller, 0, 1, 4, GridBagConstraints.HORIZONTAL);

        constrain(createJButton("Students"), 0, 13, 2, GridBagConstraints.BOTH);
        constrain(createJButton("Courses"), 2, 13, 2, GridBagConstraints.BOTH);
        constrain(createJButton("Undergraduate Students"), 0, 14, 2, GridBagConstraints.BOTH);
        constrain(createJButton("Postgraduate Students"), 2, 14, 2, GridBagConstraints.BOTH);

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
            if (command.equals("Students"))
                students();
            else if (command.equals("Undergraduate Students"))
                undergraduateStudents();
            else if(command.equals("Postgraduate Students"))
                postgraduateStudents();
            else if (command.equals("Courses"))
                courses();
        }
        catch (Error error) {
            studentApplication.setStatus("Error occurred: " + error.getMessage());
        }
        catch (Exception exception) {
            studentApplication.setStatus("Exception occurred: " + exception.getMessage());
        }
    }

    /**
     * Method students
     *
     */

    private void students() {
        try {
            viewLabel.setText("List of All Students");
            viewField.setText(university.getStudents());
        }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("getStudents() not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("University.class not found.");
        }
    }

    /**
     * Method undergraduateStudents
     *
     */

    private void undergraduateStudents() {
        try {
            viewLabel.setText("List of All Undergraduate Students");
            viewField.setText(university.getUndergraduateStudents());

        }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("getUndergraduateStudents() not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("University.class not found.");
        }
    }

    /**
     * Method postgraduateStudents
     *
     */

    private void postgraduateStudents() {
        try {
            viewLabel.setText("List of All Postgraduate Students");
            viewField.setText(university.getPostgraduateStudents());
        }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("getPostgraduateStudents() not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("University.class not found.");
        }
    }

    /**
     * Method courses
     *
     */

    private void courses() {
        try {
            viewField.setText(university.getCourses());
            viewLabel.setText("List of All Courses");
        }
        catch (NoSuchMethodError error) {
            studentApplication.setStatus("getCourses() not implemented.");
        }
        catch (NoClassDefFoundError error) {
            studentApplication.setStatus("University.class not found.");
        }
    }
}
