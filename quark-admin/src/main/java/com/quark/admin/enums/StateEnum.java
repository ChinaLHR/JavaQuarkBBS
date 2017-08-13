package com.quark.admin.enums;

public enum StateEnum {

    SUCCESS(200),ERROR(500);

    private int state;

    StateEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
