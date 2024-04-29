package com.nexthink.inheritance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MouseTest {

    private ObjectMapper mapper;
    private JsonSchema jsonSchema;

    @SneakyThrows
    public MouseTest() {
        mapper = new ObjectMapper();
        jsonSchema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4)
                .getSchema(MouseTest.class.getResourceAsStream("/inheritance/schema.json"));
    }

    @Test
    @SneakyThrows
    public void mouseShouldHaveWheelCount() {
        val jsonNode = mapper.readTree(
                MouseTest.class.getResourceAsStream("/inheritance/mouse_invalid.json"));
        assertThat(jsonSchema.validate(jsonNode)).isNotEmpty().asString()
                .contains("$.buttonCount: should not be valid to the schema \"not\" : {}");
    }

    @Test
    @SneakyThrows
    public void mouseShouldNotHaveUnexpectedType() {
        val jsonNode = mapper.readTree(
                MouseTest.class.getResourceAsStream("/inheritance/mouse_invalid_type.json"));
        assertThat(jsonSchema.validate(jsonNode)).isNotEmpty().asString()
                .contains("$.type: does not have a value in the enumeration [mouse]");
    }

    @Test
    @SneakyThrows
    public void mouseShouldNotHaveKeyboardType() {
        val jsonNode = mapper.readTree(
                MouseTest.class.getResourceAsStream("/inheritance/mouse_invalid_type_v2.json"));
        assertThat(jsonSchema.validate(jsonNode)).isNotEmpty().asString()
                .contains("[$.type: does not have a value in the enumeration [mouse]]");
    }

    @Test
    @SneakyThrows
    public void validMouse() {
        val jsonNode = mapper.readTree(MouseTest.class.getResourceAsStream("/inheritance/mouse_valid.json"));
        assertThat(jsonSchema.validate(jsonNode)).isEmpty();
        val mouse = mapper.readValue(MouseTest.class.getResourceAsStream("/inheritance/mouse_valid.json"), Mouse.class);
        // data world
        assertThat(mouse.getName()).isEqualTo("supermouse");
        assertThat(mouse.getButtonCount()).isEqualTo(1);
        assertThat(mouse.getWheelCount()).isEqualTo(2);
        // object-oriented world
        assertTrue(mouse.getDriver() instanceof MouseDriver);
        assertThat(mouse.getDriver().getDriverInfo()).isEqualTo("I am a mouse");
    }
}
