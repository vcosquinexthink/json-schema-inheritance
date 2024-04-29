package com.nexthink.inheritance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Keyboard extends Peripheral {
    private Integer keyCount;

    @Override
    public Driver getDriver() {
        return new KeyboardDriver();
    }
}
