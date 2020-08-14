package com.smu.rest.library.api.controllers.errorhandlers;

import com.smu.rest.library.api.controllers.reponses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandleConstraintViolation {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> errorHandler(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> messages = constraintViolations.stream().map(cv -> cv.getPropertyPath()+": "+ cv.getMessage()).collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(messages);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
