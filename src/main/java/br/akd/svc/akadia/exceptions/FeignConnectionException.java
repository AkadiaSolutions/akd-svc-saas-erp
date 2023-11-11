package br.akd.svc.akadia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FeignConnectionException extends RuntimeException {
    public FeignConnectionException(String message) {
        super(message);
    }
}
