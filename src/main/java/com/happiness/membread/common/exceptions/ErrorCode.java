package com.happiness.membread.common.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    UNKNOWNERROR(3000,"Unknown exception"),
    NOTFOUND(2000,"Not found"),
    WRONGPASSWORD(2001,"")
    ;

    int code;
    String message;


}
