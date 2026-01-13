package com.flights.Flights;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> NotNullable_Handle(MethodArgumentNotValidException ex)
    {
        JSONObject jsonObject = new JSONObject();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                jsonObject.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(jsonObject.toString());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> InvalidInputFromEndPoint_Handle(HttpMessageNotReadableException ex) {

        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException ife) {
            Class<?> targetType = ife.getTargetType();
            String fieldName = ife.getPath().get(0).getFieldName();
            Object invalidValue = ife.getValue();

            return ResponseEntity.badRequest().body(
                    "Invalid value '" + invalidValue +
                            "' for field '" + fieldName +
                            "'. Expected type: " + targetType.getSimpleName()
            );
        }

        if (cause instanceof MismatchedInputException mie) {
            Class<?> targetType = mie.getTargetType();

            return ResponseEntity.badRequest().body(
                    "JSON type mismatch. Expected type: " +
                            targetType.getSimpleName()
            );
        }

        return ResponseEntity.badRequest().body("Malformed JSON request");
    }

    @ExceptionHandler(FlightException.class)
    public ResponseEntity<String> Flight_Handle(FlightException ex)
    {
        return  ResponseEntity.badRequest().body(ex.getMessage());
    }
}
