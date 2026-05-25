package com.example.CRUDbasis.exception;

public enum ErrorCode {
    UserExists(1002,"this user already exists"),
    USER_NOT_FOUND(1003,"User not found"),
    UNCATEGORIZE_DEXCEPTION(9999,"uncategorized exception"),
    INVAILD_KEY(1006,"Invaild message key"),
    AUTHENTICATED_FAILD(1007,"authenticated faild"),
    PASSWORD_INVAILD(1004,"password must be at least 8 characters"),
    USERNAME_INVAILD(1005,"username must be at least 6 characters");
    private int code ;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
