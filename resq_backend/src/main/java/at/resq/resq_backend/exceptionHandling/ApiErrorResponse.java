package at.resq.resq_backend.exceptionHandling;


import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 25.04.2026
 * Time: 18:03
 */

@Value
@Builder
public class ApiErrorResponse {
    int status;
    String error;
    String message;
    String path;
    LocalDateTime timestamp;
}
