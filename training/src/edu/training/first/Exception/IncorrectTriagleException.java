package edu.training.first.Exception;

/**
 * Created by Dell on 26.09.2016.
 */
public class IncorrectTriagleException extends Exception{
    public IncorrectTriagleException() {
        super();
    }

    public IncorrectTriagleException(String message) {
        super(message);
    }

    public IncorrectTriagleException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectTriagleException(Throwable cause) {
        super(cause);
    }

    protected IncorrectTriagleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
