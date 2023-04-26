package com.example.displaymybirthday.advisor;

import com.example.displaymybirthday.exception.NotFoundException;
import com.example.displaymybirthday.util.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardResponse> handleInvalidArgument(ConstraintViolationException exception){


        List<String> errorMap = new ArrayList<>();

        LOGGER.info(exception.getConstraintViolations().toString());
        for (ConstraintViolation<?> error : exception.getConstraintViolations()) {
            errorMap.add(error.getMessage());
        }

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(500, "VALIDATION ERROR", errorMap ), HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<StandardResponse> handleInvalidDate(DateTimeParseException exception){

        LOGGER.info(exception.toString());

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(500, "VALIDATION ERROR", exception ), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "ERROR COMING", e.getMessage() ), HttpStatus.NOT_FOUND);
    }

}
