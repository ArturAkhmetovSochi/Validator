import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
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
    void testNumberSchemaWithNull() {
        var v = new Validator();
        var schema = v.number();
        assertTrue(schema.isValid(null));
    }

    @Test
    void testNumberSchemaWithRequired() {
        var v = new Validator();
        var schema = v.number().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(1));
    }

    @Test
    void testNumberSchemaWithPositive() {
        var v = new Validator();
        var schema = v.number().positive();
        assertFalse(schema.isValid(-2));
        assertTrue(schema.isValid(2));
    }

    @Test
    void testNumberSchemaWithRange() {
        var v = new Validator();
        var schema = v.number().range(1, 5);
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(6));
        assertTrue(schema.isValid(1));
        assertTrue(schema.isValid(5));
    }

    @Test
    void testNumberSchemaWithAllRestrictions() {
        var v = new Validator();
        var schema = v.number().required().positive().range(10, 20);
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(9));
        assertTrue(schema.isValid(20));
    }

    @Test
    void testMapSchemaNoRequired() {
        var v = new Validator();
        var schema = v.map();
        assertTrue(schema.isValid(null));
    }

    @Test
    void testMapSchemaWithRequired() {
        var v = new Validator();
        var schema = v.map().required();
        assertFalse(schema.isValid(null));
    }

    @Test
    void testMapSchemaWithSizeOf() {
        var v = new Validator();
        var schema = v.map().sizeof(2);
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        assertFalse(schema.isValid(map));
        map.put("k2", "v2");
        assertTrue(schema.isValid(map));
        map.put("k3", "v3");
        assertFalse(schema.isValid(map));
    }

    @Test
    void testShapeWithStringSchema() {
        var v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2).contains("m"));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "m");
        assertFalse(schema.isValid(human3));
    }

    @Test
    void testShapeWithNumberSchema() {
        var v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();

        schemas.put("house number", v.number().required());
        schemas.put("age", v.number().required().positive().range(18, 60));

        schema.shape(schemas);

        Map<String, Integer> human1 = new HashMap<>();
        human1.put("house number", 455);
        human1.put("age", 55);
        assertTrue(schema.isValid(human1));

        Map<String, Integer> human2 = new HashMap<>();
        human2.put("house number", 25);
        human2.put("age", 17);
        assertFalse(schema.isValid(human2));

        Map<String, Integer> human3 = new HashMap<>();
        human2.put("house number", 25);
        human2.put("age", null);
        assertFalse(schema.isValid(human3));
    }

    @Test

    void testShapeWithEmptyMap() {
        Validator v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schema.shape(schemas);
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    public void testStringValidator() {
        Validator v = new Validator();
        StringSchema schema = v.string();

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
    }
}
