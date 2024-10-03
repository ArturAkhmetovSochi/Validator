import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
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
}
