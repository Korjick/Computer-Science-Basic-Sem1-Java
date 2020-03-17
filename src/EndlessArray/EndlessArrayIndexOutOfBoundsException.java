package EndlessArray;

public class EndlessArrayIndexOutOfBoundsException extends Exception {
    public EndlessArrayIndexOutOfBoundsException() {
        super("An attempt was made to get an item at an unavailable index");
    }

    public EndlessArrayIndexOutOfBoundsException(String message) {
        super(message);
    }

    public EndlessArrayIndexOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EndlessArrayIndexOutOfBoundsException(Throwable cause) {
        super(cause);
    }
}
