package br.akd.svc.akadia.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<StandartError> invalidRequestException(HttpServletRequest req,
                                                                 InvalidRequestException invalidRequestException) {
        StandartError standartError = StandartError.builder()
                .localDateTime(LocalDateTime.now())
                .status(400)
                .error(invalidRequestException.getMessage())
                .path(req.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FeignConnectionException.class)
    public ResponseEntity<StandartError> feignConnectionException(HttpServletRequest req,
                                                                  FeignConnectionException feignConnectionException) {
        StandartError standartError = StandartError.builder()
                .localDateTime(LocalDateTime.now())
                .status(500)
                .error(feignConnectionException.getMessage())
                .path(req.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standartError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFoundException(HttpServletRequest req,
                                                                 ObjectNotFoundException objectNotFoundException) {
        StandartError standartError = StandartError.builder()
                .localDateTime(LocalDateTime.now())
                .status(400)
                .error(objectNotFoundException.getMessage())
                .path(req.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standartError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<StandartError> unauthorizedAccessException(HttpServletRequest req,
                                                                     UnauthorizedAccessException unauthorizedAccessException) {
        StandartError standartError = StandartError.builder()
                .localDateTime(LocalDateTime.now())
                .status(401)
                .error(unauthorizedAccessException.getMessage())
                .path(req.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standartError);
    }

    @Override
    @Nonnull
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  @Nonnull HttpHeaders headers,
                                                                  @Nonnull HttpStatus status,
                                                                  @Nonnull WebRequest request) {
        String mensagemErro = ex.getMessage();
        if (mensagemErro != null && mensagemErro.contains("Required request body is missing"))
            mensagemErro = "O corpo da requisição não pode ser nulo";

        StandartError standartError = StandartError.builder()
                .localDateTime(LocalDateTime.now())
                .status(400)
                .error(mensagemErro)
                .path(request.getContextPath())
                .build();
        return ResponseEntity.status(status).body(standartError);
    }

    @Override
    @Nonnull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@Nonnull MethodArgumentNotValidException ex,
                                                                  @Nonnull HttpHeaders headers,
                                                                  @Nonnull HttpStatus status,
                                                                  @Nonnull WebRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));

        StandartError standartError = StandartError.builder()
                .localDateTime(LocalDateTime.now())
                .status(400)
                .error(errors.toString())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();
        return ResponseEntity.status(status).body(standartError);
    }

}
