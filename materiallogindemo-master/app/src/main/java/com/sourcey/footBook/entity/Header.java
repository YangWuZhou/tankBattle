package com.sourcey.footBook.entity;

public class Header {
    private int payloadSize;
    private int type;
    private int sender;
    private int receiver;
    //与Code中的枚举进行比较
    private byte status;

    public static final int HEADER_SIZE = 17;

    public Header() {
        this.payloadSize = 0;
        this.type = 0;
        this.sender = 0;
        this.receiver = 0;
        this.status = 0;
    }

    public int getPayloadSize() {
        return payloadSize;
    }

    public void setPayloadSize(int payloadSize) {
        this.payloadSize = payloadSize;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
