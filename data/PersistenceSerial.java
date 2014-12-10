package data;

import business.*;

import java.io.*;

public class PersistenceSerial implements Persistence {
    private University university;

    public PersistenceSerial(University university) {
        this.university = university;
    }

    public void read() throws Exception {
        university.read();
    }

    public void save() throws Exception {
        university.save();
    }
}
