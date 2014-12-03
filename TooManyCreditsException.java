public class TooManyCreditsException extends Exception {
    public TooManyCreditsException() {
        super("Student course limit has been exceeded.");
    }

    public TooManyCreditsException(String message) {
        super(message);
    }
}
