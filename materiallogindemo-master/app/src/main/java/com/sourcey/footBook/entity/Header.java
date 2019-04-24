package com.sourcey.footBook.entity;

public class Header {
    private int payloadSize;
    private long sender;
    private long receiver;
    private byte type;
    //与Code中的枚举进行比较
    private byte status;

    public static final int HEADER_SIZE = 22;

    public Header() {
        this.payloadSize = 0;
        this.sender = 0L;
        this.receiver = 0L;
        this.type = 0;
        this.status = 0;
    }

    public int getPayloadSize() {
        return payloadSize;
    }

    public void setPayloadSize(int payloadSize) {
        this.payloadSize = payloadSize;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
