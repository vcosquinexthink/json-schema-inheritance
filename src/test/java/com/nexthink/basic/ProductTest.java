package com.nexthink.basic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductTest {

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void invalidPriceProduct() throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(
                ProductTest.class.getResourceAsStream("/basic/schema.json"));
        JsonNode jsonNode = mapper.readTree(
                ProductTest.class.getResourceAsStream("/basic/product_invalid.json"));
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        assertThat(errors).isNotEmpty().asString().contains("price: must have a minimum value of 0");
    }

    @Test
    public void validProduct() throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(
                ProductTest.class.getResourceAsStream("/basic/schema.json"));
        JsonNode jsonNode = mapper.readTree(
                ProductTest.class.getResourceAsStream("/basic/product_valid.json"));
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        assertThat(errors).isEmpty();
    }
}
