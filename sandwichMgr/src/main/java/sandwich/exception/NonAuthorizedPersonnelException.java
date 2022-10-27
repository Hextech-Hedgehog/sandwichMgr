package sandwich.exception;

public class NonAuthorizedPersonnelException extends Exception {
    public NonAuthorizedPersonnelException(String message) {
        super(message);
    }
    public NonAuthorizedPersonnelException() {
        super("Non authorized personnel access to data permission denied");
    }
}
