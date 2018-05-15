package com.wtu.demo.constants;

public enum RankEnum {

    ASC(Constants.ENUM_INCREASE),
    DESC(Constants.ENUM_DECREASE);

    private String value;

    private RankEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}