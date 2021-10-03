package com.ssafy.checklist.global.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    CLIENT_ERROR(400,"COMMON-ERR-400","CLIENT ERROR"),
    ;

    private int status;
    private String errorCode;
    private String message;
}
