package com.tpkhanh.chatappapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCOUNT_EXISTED(1001, "Account already exists", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND(1002, "Account not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1003, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1004, "Unauthorized", HttpStatus.FORBIDDEN),
    ID_USER_EXISTED(1005, "Id User already exists", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1006, "Invalid password", HttpStatus.BAD_REQUEST),
    ID_USER_NOT_EXISTED(1007, "Id User not already exists", HttpStatus.BAD_REQUEST),
    ACCOUNT_HAS_BEEN_LOCK(1008, "Account has been lock", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_LOCK(1009, "Account not lock", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1010, "User not found", HttpStatus.NOT_FOUND)
    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
