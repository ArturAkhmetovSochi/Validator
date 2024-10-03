import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    @Test
    void testStringSchemaWithNull() {
        var v = new Validator();
        var schema = v.string();
        assertTrue(schema.isValid(null));
    }

    @Test
    void testStringSchemaWithEmptyString() {
        var v = new Validator();
        var schema = v.string();
        assertTrue(schema.isValid(""));
    }

    @Test
    void testStringSchemaWithRequired() {
        var v = new Validator();
        var schema = v.string().required();
        assertTrue(schema.isValid("abc"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    void testStringSchemaWithMinLength() {
        var v = new Validator();
        var schema = v.string().minLength(5);
        assertFalse(schema.isValid("aq"));
        assertFalse(schema.isValid("abcd"));
        assertTrue(schema.isValid("Hexle"));
    }

    @Test
    void testStringSchemaWithContains() {
        var v = new Validator();
        var schema = v.string().contains("abc");
        assertFalse(schema.isValid("bbc"));
        assertTrue(schema.isValid("contain abc"));
        assertFalse(schema.isValid("aklpobkjc"));
    }

    @Test
    void testStringSchemaWithAllRestrictions() {
        var v = new Validator();
        var schema = v.string().required().minLength(7).contains("map");
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("qwertyuio"));
        assertFalse(schema.isValid("go map"));
        assertTrue(schema.isValid("Use map for MapSchema"));
    }

    @Test
    public void testStringValidator() {
        var v = new Validator();
        var schema = v.string();

        assertThat(schema.isValid("")).isTrue();

        schema.required();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();

        schema.minLength(7);
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isFalse();

        assertThat(
                schema.contains("what").isValid("what does the fox say")
        ).isTrue();

        assertThat(
                schema.contains("whatthe").isValid("what does the fox say")
        ).isFalse();

        var schema1 = v.string().required().minLength(10).minLength(4);
        assertThat(schema1.isValid("hexlet")).isTrue();
    }

}
