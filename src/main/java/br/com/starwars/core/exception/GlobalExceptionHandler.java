package br.com.starwars.core.exception;

import br.com.starwars.core.dto.ResponseErrorDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(new ResponseErrorDTO(errors), status);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseErrorDTO> handleBusinessException(BadRequestException ex, WebRequest request) {
        List<String> errors = List.of(Optional.ofNullable(ex.getCause()).map(Object::toString)
                .orElse(ex.getMessage()));
        return new ResponseEntity<>(new ResponseErrorDTO(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseErrorDTO> handleNotFoundException(NotFoundException ex) {
        List<String> errors = List.of(ex.getMessage());
        return new ResponseEntity<>(new ResponseErrorDTO(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDTO> handleInternalServerErrorException(Exception ex) {
        List<String> errors = List.of(Optional.ofNullable(ex.getCause()).map(Object::toString)
                .orElse(ex.getMessage()));
        return new ResponseEntity<>(new ResponseErrorDTO(errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}