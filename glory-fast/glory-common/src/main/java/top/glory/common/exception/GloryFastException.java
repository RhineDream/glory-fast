package top.glory.common.exception;

public class GloryFastException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GloryFastException(String message) {
        super(message);
    }

    public GloryFastException(Throwable cause) {
        super(cause);
    }

    public GloryFastException(String message, Throwable cause) {
        super(message, cause);
    }
}
