package com.happiness.membread.common.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    UNKNOWN_ERROR(3000,"Unknown exception",400),
    NOTFOUND(2000,"Not found",400),
    WRONGPASSWORD(2001,"",401),
    METHOD_INVALID(4000,"Method invalid",400)
    ;

    final int code;
    final String message;
    final int statusCode;

}
