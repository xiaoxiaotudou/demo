package com.wtu.demo.constants;

public enum PageSizeEnum {

    FIVE(5),
    TEN(10),
    FIFTEEN(15),
    TWENTY(20);

    private int value;

    private PageSizeEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static PageSizeEnum valueOf(int value) {
        switch (value) {
            case 5:
                return FIVE;
            case 10:
                return TEN;
            case 15:
                return FIFTEEN;
            case 20:
                return TWENTY;
            default:
                throw new IllegalArgumentException();
        }
    }
}