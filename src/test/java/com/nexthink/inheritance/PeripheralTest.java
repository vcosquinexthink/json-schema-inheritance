package com.nexthink.inheritance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class PeripheralTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void device() throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonNode jsonNode = mapper.readTree(
                PeripheralTest.class.getResourceAsStream("/inheritance/peripheral_invalid.json"));
        Set<ValidationMessage> errors = factory.getSchema(
                PeripheralTest.class.getResourceAsStream("/inheritance/schema.json")).validate(jsonNode);
        assertThat(errors).isNotEmpty();
    }
}
