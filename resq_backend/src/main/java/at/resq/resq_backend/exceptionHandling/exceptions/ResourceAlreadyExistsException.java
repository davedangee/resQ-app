package at.resq.resq_backend.exceptionHandling.exceptions;


/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 25.04.2026
 * Time: 18:02
 */

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
