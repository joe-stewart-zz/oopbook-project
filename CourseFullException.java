public class CourseFullException extends Exception {
    public CourseFullException() {
        super("Course enrollment limit reached. No more students can be registered.");
    }

    public CourseFullException(String message) {
        super(message);
    }
}
