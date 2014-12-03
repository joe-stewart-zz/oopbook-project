import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 * StudentApplication
 *
 */

public class StudentApplication extends JFrame implements ActionListener, ChangeListener, Runnable {
    private static StudentApplication instance;
    private University university;
    private JTabbedPane tabbedPane;
    private JLabel statusBar;

    /**
     * Method StudentApplication
     *
     */

    public StudentApplication() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("File");
        menu.add(createJMenuItem("Reset"));
        menu.add(createJMenuItem("Exit"));
        menuBar.add(menu);

        menu = new JMenu("Help");
        menu.add(createJMenuItem("Usage"));
        menu.add(createJMenuItem("About"));
        menuBar.add(menu);

        menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
        setJMenuBar(menuBar);

        statusBar = new JLabel("Ready");
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(statusBar, BorderLayout.SOUTH);
        tabbedPane = new JTabbedPane();
        tabbedPane.addChangeListener(this);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        setTitle("Student Management System");
        pack();
        setSize(490, 460);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            university = new University("The University of Computing");
        }
        catch(NoClassDefFoundError error){
            setStatus("University.class not found.");
        }
        new Thread(this).start();
    }

    /**
     * Method actionPerformed
     *
     *
     * @param event
     *
     */

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        setStatus(command + " menu selected.");
        if (command.equals("Exit"))
            System.exit(0);
        else if (command.equals("Reset"))
            reset();
        else if(command.equals("Usage"))
            popup("Student Management System", "Manage information on students and courses.\nEnter data in relevant fields to perform functions.\nData in irrelevant fields will be ignored.\nSee User's Guide for more details.");
        else if(command.equals("About")) popup("About Student Management System", "Object-Oriented Programming in Java\nAuthor: Permanand Mohan (c) 2013");
    }

    /**
     * Method createJMenuItem
     *
     *
     * @param name
     *
     */

    private JMenuItem createJMenuItem(String name) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(this);
        return item;
    }

    /**
     * Method popup
     *
     *
     * @param title
     * @param message
     *
     */

    private void popup (String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method reset
     *
     */

    public void reset() {
        university = new University("The University of Computing");
        tabbedPane.removeAll();
        tabbedPane.addTab("Students", new StudentPanel(this, university));
        tabbedPane.addTab("Courses", new CoursePanel(this, university));
        tabbedPane.addTab("View All", new ViewAllPanel(this, university));
    }

    /**
     * Method run
     *
     */

    public void run() {
        tabbedPane.addTab("Students", new StudentPanel(this, university));
        tabbedPane.addTab("Courses", new CoursePanel(this, university));
        tabbedPane.addTab("View All", new ViewAllPanel(this, university));
    }

    /**
     * Method setStatus
     *
     *
     * @param message
     *
     */

    protected void setStatus(String message) {
        statusBar.setText(message);
    }

    /**
     * Method stateChanged
     *
     *
     * @param event
     *
     */

    public void stateChanged(ChangeEvent event) {
        setStatus("Ready.");
    }

    public static void main(String[] args) {
        StudentApplication sa = new StudentApplication();
    }
}
