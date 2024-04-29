package com.nexthink.inheritance;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Keyboard extends Peripheral {
    private Integer keyCount;

    @Override
    public Driver getDriver() {
        return new KeyboardDriver();
    }
}
