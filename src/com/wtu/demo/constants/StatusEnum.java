package com.wtu.demo.constants;

public enum StatusEnum {

    CREATE(Constants.ENUM_CREATE),
    EDIT(Constants.ENUM_EDIT),
    VIEW(Constants.ENUM_VIEW);

    private String value;

    private StatusEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}