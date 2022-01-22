package com.socket.exception;

import com.socket.payload.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Response response;

    // custom
    @ExceptionHandler(java.lang.Error.class)
    public ResponseEntity<?> error(Error err) {
        return response.status(err.getStatus()).message(err.getMessage()).end();
    }

    //@valid exception
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> l = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            l.add(error.getDefaultMessage());
        });
        StringBuilder err = new StringBuilder();
        for (String s : l) {
            err.append(s).append(", ");
        }
        return response.status(400).message(err.toString()).end();
    }

    // CRUD
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> conflict(DataIntegrityViolationException e) {
        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        return response.status(403).message(message).end();
    }
    
}
