package com.happiness.membread.common.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppException extends RuntimeException{
    private ErrorCode errorCode;
}
