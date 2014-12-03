public class PostgraduateStudent extends Student {
    private String thesisTitle;
    private String supervisor;

    public PostgraduateStudent(long ID, String name, String address, String phone, String email, String thesisTitle, String supervisor) {
        super(ID, name, address, phone, email);
        this.thesisTitle = thesisTitle;
        this.supervisor = supervisor;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String toString() {
        String s;

        s = super.toString() + " Thesis: " + thesisTitle + " Supervisor: " + supervisor;

        return s;
    }
}
