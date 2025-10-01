package br.com.desafio.consultacredito.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Getter
public class ResponseError {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final OffsetDateTime timestamp = OffsetDateTime.now();
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    public ResponseError(HttpStatus status, String message, String path) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

}