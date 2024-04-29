package com.nexthink.inheritance;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Mouse extends Peripheral {
    private Integer buttonCount;
    private Integer wheelCount;

    @Override
    public Driver getDriver() {
        return new MouseDriver();
    }
}
