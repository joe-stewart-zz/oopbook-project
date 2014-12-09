package business;

public class NoSuchRoleException extends Exception {
    public NoSuchRoleException() {
        super("Role requested does not exist.");
    }
    public NoSuchRoleException(String message) {
        super(message);
    }
}
