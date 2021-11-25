package ua.com.alevel.starteducation.exceptions;

public class InvalidRefreshTokenException extends Exception{
    public InvalidRefreshTokenException() {
        super();
    }

    public InvalidRefreshTokenException(Throwable cause) {
        super(cause);
    }
}
