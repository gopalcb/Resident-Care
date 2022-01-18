package com.emailmanager.emailmanagerspringbootkafka.entity;

public class Message {
    private Object header;
    private Object value;

    public Object getHeader() {
        return header;
    }

    public Object getValue() {
        return value;
    }

    public void setHeader(Object header) {
        this.header = header;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
