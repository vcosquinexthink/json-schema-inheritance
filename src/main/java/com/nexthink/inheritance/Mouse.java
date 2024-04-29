package com.nexthink.inheritance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mouse extends Peripheral {
    private Integer buttonCount;
    private Integer wheelCount;

    @Override
    public Driver getDriver() {
        return new MouseDriver();
    }
}
