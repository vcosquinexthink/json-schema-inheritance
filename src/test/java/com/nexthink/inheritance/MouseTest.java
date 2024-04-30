package com.nexthink.inheritance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion.VersionFlag;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MouseTest {

    public static final String PERIPHERAL_SCHEMA = "/inheritance/schema.json";
    public static final String INVALID_MOUSE = "/inheritance/mouse_invalid.json";
    public static final String INVALID_TYPE_MOUSE = "/inheritance/mouse_invalid_type.json";
    public static final String INVALID_ANOTHER_TYPE_MOUSE = "/inheritance/mouse_invalid_type_v2.json";
    public static final String VALID_MOUSE = "/inheritance/mouse_valid.json";

    private ObjectMapper mapper;
    private JsonSchema jsonSchema;

    @SneakyThrows
    public MouseTest() {
        mapper = new ObjectMapper();
        jsonSchema = JsonSchemaFactory.getInstance(VersionFlag.V4)
                .getSchema(MouseTest.class.getResourceAsStream(PERIPHERAL_SCHEMA));
    }

    @Test
    @SneakyThrows
    public void mouseShouldHaveWheelCount() {
        val jsonNode = mapper.readTree(
                MouseTest.class.getResourceAsStream(INVALID_MOUSE));
        assertThat(jsonSchema.validate(jsonNode)).isNotEmpty().asString()
                .contains("$.buttonCount: should not be valid to the schema \"not\" : {}");
    }

    @Test
    @SneakyThrows
    public void mouseShouldNotHaveUnexpectedType() {
        val jsonNode = mapper.readTree(
                MouseTest.class.getResourceAsStream(INVALID_TYPE_MOUSE));
        assertThat(jsonSchema.validate(jsonNode)).isNotEmpty().asString()
                .contains("$.type: does not have a value in the enumeration [mouse]");
    }

    @Test
    @SneakyThrows
    public void mouseShouldNotHaveKeyboardType() {
        val jsonNode = mapper.readTree(
                MouseTest.class.getResourceAsStream(INVALID_ANOTHER_TYPE_MOUSE));
        assertThat(jsonSchema.validate(jsonNode)).isNotEmpty().asString()
                .contains("[$.type: does not have a value in the enumeration [mouse]]");
    }

    @Test
    @SneakyThrows
    public void validMouse() {
        val jsonNode = mapper.readTree(MouseTest.class.getResourceAsStream(VALID_MOUSE));
        assertThat(jsonSchema.validate(jsonNode)).isEmpty();

        val mouse = mapper.readValue(MouseTest.class.getResourceAsStream(VALID_MOUSE), Mouse.class);
        // data world
        assertThat(mouse.getName()).isEqualTo("supermouse");
        assertThat(mouse.getButtonCount()).isEqualTo(1);
        assertThat(mouse.getWheelCount()).isEqualTo(2);

        // object-oriented world
        assertTrue(mouse.getDriver() instanceof MouseDriver);
        assertThat(mouse.getDriver().getDriverInfo()).isEqualTo("I am a mouse");
    }
}
