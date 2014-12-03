public abstract class Student {
    private long ID;
    private String name;
    private String address;
    private String phone;
    private String email;

    public Student(long ID, String name, String address, String phone, String email) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public boolean equals(Object o) {
        if(o instanceof Student) {
            Student s = (Student) o;
            if(this.ID == s.getID())
                return true;
            else
                return false;
        }
        else
            throw new IllegalArgumentException("Argument is not of type Student.");
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    } 

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        String s;
        s = "ID: " + ID + " Name: " + name + " Address: " + address + " Phone: " + phone + " Email: " + email;
        return s;
    }

    public static void main(String[] args) {
    }
}
