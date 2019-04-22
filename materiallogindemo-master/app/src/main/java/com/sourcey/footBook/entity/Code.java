package com.sourcey.footBook.entity;

public enum Code {
    OK(0), MSG_ERROR(1), HTTP_ERROR(2),
    DB_ERROR(3), INVALID_ACCOUNT(4),
    INVALID_PASSWORD(5), INVALID_DATA(6);
    private int status;
    Code(int status) {
        this.status = status;
    }

    public byte getStatus() {
        return (byte) status;
    }
}
