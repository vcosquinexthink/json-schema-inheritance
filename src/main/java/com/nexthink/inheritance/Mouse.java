package com.nexthink.inheritance;

public class Mouse extends Peripheral {
    private final Integer buttonCount;
    private final Integer wheelCount;

    public Mouse(String name, Integer buttonCount, Integer wheelCount) {
        super(name);
        this.buttonCount = buttonCount;
        this.wheelCount = wheelCount;
    }
}
