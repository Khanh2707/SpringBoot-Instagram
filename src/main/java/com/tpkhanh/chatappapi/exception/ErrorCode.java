package com.tpkhanh.chatappapi.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    ACCOUNT_EXISTED(1001, "Account already exists"),
    ACCOUNT_NOT_FOUND(1002, "Account not found"),
    UNAUTHENTICATED(1003, "Unauthenticated"),
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
