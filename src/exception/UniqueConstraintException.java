package exception;

public class UniqueConstraintException extends Exception{
    private String message;

    public UniqueConstraintException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return message;
    }
}
