package com.nexthink.inheritance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PeripheralTest {

    private final ObjectMapper mapper;
    private final JsonSchema jsonSchema;

    @SneakyThrows
    public PeripheralTest() {
        mapper = new ObjectMapper();
        jsonSchema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4)
                .getSchema(PeripheralTest.class.getResourceAsStream("/inheritance/schema.json"));
    }

    @Test
    @SneakyThrows
    public void deviceIsAbstract() {
        val jsonNode = mapper.readTree(
                PeripheralTest.class.getResourceAsStream("/inheritance/peripheral_invalid.json"));
        assertThat(jsonSchema.validate(jsonNode)).isNotEmpty();
    }

    @Test
    @SneakyThrows
    public void readAsPeripheral() {
        val jsonNode = mapper.readTree(
                PeripheralTest.class.getResourceAsStream("/inheritance/mouse_valid.json"));
        assertThat(jsonSchema.validate(jsonNode)).isEmpty();
        val mouse = mapper.readValue(PeripheralTest.class.getResourceAsStream("/inheritance/mouse_valid.json"), Peripheral.class);
        // object-oriented world
        assertTrue(mouse.getDriver() instanceof Driver);
    }

    @Test
    @SneakyThrows
    public void allPeripheralsProvideADriver() {
        val jsonNode = mapper.readTree(
                PeripheralTest.class.getResourceAsStream("/inheritance/peripherals_valid.json"));
        jsonSchema.validate(jsonNode).forEach(node ->
                assertThat(jsonSchema.validate(jsonNode).isEmpty())
        );

        val peripherals = mapper.readValue(PeripheralTest.class.getResourceAsStream("/inheritance/peripherals_valid.json"), new TypeReference<List<Peripheral>>() {});
        // object-oriented world
        peripherals.forEach(peripheral ->
                assertTrue(peripheral.getDriver() instanceof Driver));
    }
}
