package com.bootcamp.bootcamp17.exception;

import com.bootcamp.bootcamp17.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice // annotation untuk membuat class yang bisa menangani exception secara global
public class GlobalAdviceException {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse<Object>> handleBadRequest(BadRequestException exception){
        BaseResponse<Object> response = new BaseResponse<>();
        response.setStatus("F");
        response.setMessage(exception.getMessage());
        response.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handleDataNotFound(DataNotFoundException exception){
        BaseResponse<Object> response = new BaseResponse<>();
        response.setStatus("F");
        response.setMessage(exception.getMessage());
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        ArrayList<String> errorMessages = new ArrayList<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach( error ->
                        errorMessages.add(error.getDefaultMessage())
                );
        BaseResponse<Object> response = new BaseResponse<>();
        response.setStatus("F");
        response.setMessage("Validation error");
        response.setData(errorMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
