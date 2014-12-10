package data;

import business.*;

public interface Persistence {
    public abstract void read() throws Exception;
    public abstract void save() throws Exception;
}
