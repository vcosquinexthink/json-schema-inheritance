package com.nexthink.inheritance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class MouseTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void invalidMouse() throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        InputStream resourceAsStream = MouseTest.class.getResourceAsStream("/inheritance/schema.json");
        JsonSchema jsonSchema = factory.getSchema(resourceAsStream);
        JsonNode jsonNode = mapper.readTree(
                MouseTest.class.getResourceAsStream("/inheritance/mouse_invalid.json"));
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        assertThat(errors).isNotEmpty().asString().contains("$.buttonCount: should not be valid to the schema \"not\" : {}");
    }

    @Test
    public void validMouse() throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        InputStream resourceAsStream = MouseTest.class.getResourceAsStream("/inheritance/schema.json");
        JsonSchema jsonSchema = factory.getSchema(
                resourceAsStream);
        JsonNode jsonNode = mapper.readTree(
                MouseTest.class.getResourceAsStream("/inheritance/mouse_valid.json"));
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        assertThat(errors).isEmpty();
    }
}
