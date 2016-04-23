package by.roman.ventskus.telegram.exception;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class NoMappingFoundException extends RuntimeException {

    public NoMappingFoundException(String message) {
        super(message);
    }

    public NoMappingFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
