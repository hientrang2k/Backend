package com.globits.Backend.constant;

public enum ErrorCodeEnum {
    BAD_REQUEST(400, "Bad Request"),
    INVALID_TOKEN(400_001, "Invalid Token"),
    INVALID_EXPIRED(400_002, "Invalid expired"),
    INVALID_PASSWORD(400_003, "Invalid password. Password is not null, its length is greater than 0, and it contains at least one non-whitespace character."),
    ERROR_CONFIRM_PASSWORD(400_004, "Error confirm password"),
    INVALID_EMAIL(400_005, "Invalid email"),
    DTO_NULL(400_006, "DTO Null"),
    INVALID_CODE(400_007, "Invalid Code"),
    EXISTS_SUBCATEGORY(400_008,"Exists Subcategory"),

    NOT_FOUND(404, "Not Found"),
    USER_NOT_FOUND(404_001, "User not found"),
    OTP_NOT_FOUND(404_002, "OTP not found"),
    CATEGORY_NOT_FOUND(404, "Category Not Found"),
    ;

    private final int errorCode;

    private final String message;

    ErrorCodeEnum(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
