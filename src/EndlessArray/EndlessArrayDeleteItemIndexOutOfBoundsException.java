package EndlessArray;

public class EndlessArrayDeleteItemIndexOutOfBoundsException extends Exception {
    public EndlessArrayDeleteItemIndexOutOfBoundsException() {
        super("An attempt was made to delete an item at an unavailable index");
    }

    public EndlessArrayDeleteItemIndexOutOfBoundsException(String message) {
        super(message);
    }

    public EndlessArrayDeleteItemIndexOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EndlessArrayDeleteItemIndexOutOfBoundsException(Throwable cause) {
        super(cause);
    }
}
