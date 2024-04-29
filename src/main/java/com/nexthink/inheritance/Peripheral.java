package com.nexthink.inheritance;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Keyboard.class, name = "keyboard"),
        @JsonSubTypes.Type(value = Mouse.class, name = "mouse")
})
public abstract class Peripheral {
    protected String name;
    protected String type;

    public abstract Driver getDriver();
}
