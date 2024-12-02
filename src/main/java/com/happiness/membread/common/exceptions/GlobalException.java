package com.happiness.membread.common.exceptions;

import com.happiness.membread.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e){
        log.error("Exception :",e);
        return ResponseEntity.badRequest()
                .body(ApiResponse
                        .builder()
                        .code(ErrorCode.UNKNOWNERROR.code)
                        .message(ErrorCode.UNKNOWNERROR.message)
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgInvalid(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest()
                .body(ApiResponse
                        .builder()
                        .code(2002)
                        .message(e.getFieldError().getDefaultMessage())
                        .build());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest()
                .body(ApiResponse
                        .builder()
                        .code(3001)
                        .message(e.getMessage())
                        .build());
    }


}
