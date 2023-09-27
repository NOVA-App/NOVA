package com.sehbeomschool.nova.global.error;

import com.sehbeomschool.nova.global.dto.ResponseDto;
import com.sehbeomschool.nova.global.error.exception.NotEnoughException;
import com.sehbeomschool.nova.global.error.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class RestControllerExceptionHandler {

    @ExceptionHandler({NotFoundException.class, NotEnoughException.class})
    public ResponseEntity<ResponseDto<String>> handleNotFoundException(
        IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDto.create(exception.getMessage())
        );
    }
}
