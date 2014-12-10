package business;

import java.util.*;
import java.io.*;

public class PersistenceText implements Persistence {
    private University university;

    public PersistenceText(University university) {
        this.university = university;
    }

    public void read() throws Exception {
        readCourses();
        readStudents();
        readUndergraduateStudents();
        readPostgraduateStudents();
        readRegistrations();
    }

    public void save() throws Exception {
        saveCourses();
        saveStudents();
        saveUndergraduateStudents();
        savePostgraduateStudents();
        saveRegistrations();
    }

    public void readCourses() throws IOException {
    }

    public void readStudents() throws IOException {
    }

    public void readUndergraduateStudents() throws IOException {
    }

    public void readPostgraduateStudents() throws IOException {
    }

    public void readRegistrations() throws IOException {
    }

    public void saveCourses() throws IOException {
    }

    public void saveStudents() throws IOException {
    }

    public void saveUndergraduateStudents() throws IOException {
    }

    public void savePostgraduateStudents() throws IOException {
    }

    public void saveRegistrations() throws IOException {
    }
}
