package business;

import java.util.Comparator;

public class StudentIDComparator implements Comparator<Student> {
    public int compare(Student student1, Student student2) {
        if(student1.getID() < student2.getID())
            return -1;
        else if(student1.getID() == student2.getID())
            return 0;
        else
            return 1;
    }
}
