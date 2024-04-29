package com.nexthink.inheritance;

import lombok.Data;

@Data
public abstract class Peripheral {
    private String name;

    public abstract Driver getDriver();
}
