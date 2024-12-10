package com.example.crud.advice;

import com.example.crud.exception.EmployeeNotFoundExcepiton;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(EmployeeNotFoundExcepiton.class)
//    public Map<String, String> handleEmployeeNotFoundEXception(EmployeeNotFoundExcepiton ex){
//        Map<String, String> errorMap = new HashMap<>();
//        errorMap.put("erroeMessage", ex.getMessage());
//        return errorMap;
//    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArgumentException(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(errors -> {
            errorMap.put(errors.getField(),errors.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(EmployeeNotFoundExcepiton.class)
    public ResponseEntity<String> handleEmployeeNotFoundEXception(EmployeeNotFoundExcepiton ex){
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}
