package com.nexthink.inheritance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PeripheralTest {

    private ObjectMapper mapper;
    private JsonSchema jsonSchema;

    @SneakyThrows
    public PeripheralTest() {
        mapper = new ObjectMapper();
        jsonSchema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4)
                .getSchema(MouseTest.class.getResourceAsStream("/inheritance/schema.json"));
    }

    @Test
    @SneakyThrows
    public void deviceIsAbstract() {
        val jsonNode = mapper.readTree(
                MouseTest.class.getResourceAsStream("/inheritance/peripheral_invalid.json"));
        assertThat(jsonSchema.validate(jsonNode)).isNotEmpty();
    }
}
