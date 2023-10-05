package com.sehbeomschool.nova.global.error;

import com.sehbeomschool.nova.domain.game.exception.AlreadyMarryException;
import com.sehbeomschool.nova.domain.game.exception.GameFinishedException;
import com.sehbeomschool.nova.domain.game.exception.GameNotFinishedException;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import com.sehbeomschool.nova.global.error.exception.AlreadyExistException;
import com.sehbeomschool.nova.global.error.exception.NotEnoughException;
import com.sehbeomschool.nova.global.error.exception.NotFoundException;
import com.sehbeomschool.nova.global.error.exception.NotVerifiedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class RestControllerExceptionHandler {

    @ExceptionHandler({NotFoundException.class, NotEnoughException.class,
        AlreadyExistException.class})
    public ResponseEntity<ResponseDto<String>> handleNotFoundException(
        IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDto.create(exception.getMessage())
        );
    }

    @ExceptionHandler({GameFinishedException.class, GameNotFinishedException.class,
        AlreadyMarryException.class})
    public ResponseEntity<ResponseDto<?>> handleGameException(
        IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDto.create(exception.getMessage())
        );
    }

    @ExceptionHandler({NotVerifiedUserException.class})
    public ResponseEntity<ResponseDto<?>> handleUserException(
        IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDto.create(exception.getMessage())
        );
    }
}
