package com.sourcey.footBook.entity;

public class Message {
    private Header header;
    private Info info;

    public Message() {}

    public Message(Header header, Info info) {
        this.header = header;
        this.info = info;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}

