package com.nexthink.inheritance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.SpecVersion.VersionFlag;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PeripheralTest {

    public static final String PERIPHERAL_SCHEMA = "/inheritance/schema.json";
    public static final String INVALID_PERIPHERAL = "/inheritance/peripheral_invalid.json";
    public static final String VALID_MOUSE = "/inheritance/mouse_valid.json";
    public static final String VALID_PERIPHERALS = "/inheritance/peripherals_valid.json";

    private final ObjectMapper mapper;
    private final JsonSchema jsonSchema;

    @SneakyThrows
    public PeripheralTest() {
        mapper = new ObjectMapper();
        jsonSchema = JsonSchemaFactory.getInstance(VersionFlag.V4)
                .getSchema(PeripheralTest.class.getResourceAsStream(PERIPHERAL_SCHEMA));
    }

    @Test
    @SneakyThrows
    public void invalidPeripheralCanNotBeDeserialized() {
        val jsonNode = mapper.readTree(
                PeripheralTest.class.getResourceAsStream(INVALID_PERIPHERAL));
        assertThat(jsonSchema.validate(jsonNode)).isNotEmpty();
    }

    @Test
    @SneakyThrows
    public void readMouseAsPeripheral() {
        val jsonNode = mapper.readTree(
                PeripheralTest.class.getResourceAsStream(VALID_MOUSE));
        assertThat(jsonSchema.validate(jsonNode)).isEmpty();

        val mouse = mapper.readValue(
                PeripheralTest.class.getResourceAsStream(VALID_MOUSE),
                Peripheral.class);
        // object-oriented world
        assertTrue(mouse.getDriver() instanceof Driver);
    }

    @Test
    @SneakyThrows
    public void allPeripheralsProvideADriver() {
        val jsonNode = mapper.readTree(
                PeripheralTest.class.getResourceAsStream(VALID_PERIPHERALS));
        assertThat(jsonSchema.validate(jsonNode).isEmpty());

        val peripherals = mapper.readValue(
                PeripheralTest.class.getResourceAsStream(VALID_PERIPHERALS),
                new TypeReference<List<Peripheral>>() {});
        // object-oriented world
        peripherals.forEach(peripheral ->
                assertTrue(peripheral.getDriver() instanceof Driver));
    }
}
