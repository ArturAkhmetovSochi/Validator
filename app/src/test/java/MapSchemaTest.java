import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {
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

}
