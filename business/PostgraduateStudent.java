package business;

public class PostgraduateStudent extends StudentRole {
    private String thesisTitle;
    private String supervisor;

    public PostgraduateStudent(Student student, String roleName, String thesisTitle, String supervisor) {
        super(roleName, student);
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

        s = this.getStudent().toString() + " Thesis: " + thesisTitle + " Supervisor: " + supervisor;

        return s;
    }
}
