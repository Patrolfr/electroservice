package komo.fraczek.electronicsservice.exception;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
class ExceptionResponse {

    @CreationTimestamp
    private LocalDateTime timestamp;

    private String message;

    private String details;

    ExceptionResponse(String message,  String details) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }
}
