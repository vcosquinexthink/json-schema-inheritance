package com.nexthink.inheritance;

public class Keyboard extends Peripheral {
    private final Integer keyCount;

    public Keyboard(String name, Integer keyCount) {
        super(name);
        this.keyCount = keyCount;
    }
}
