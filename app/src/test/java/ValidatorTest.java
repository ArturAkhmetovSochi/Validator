import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    @Test
    void testStringSchema() {

        var v = new Validator();
        var schema = v.string();
        var testStr = "what does the fox say";

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(1));

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));

        assertTrue(schema.isValid(testStr));
        assertTrue(schema.isValid("hexlet"));

        assertTrue(schema.contains("wh").isValid(testStr));
        assertTrue(schema.contains("what").isValid(testStr));
        assertFalse(schema.contains("whatthe").isValid(testStr));

        assertFalse(schema.isValid(testStr));

        assertFalse(schema.minLength(10).isValid(testStr));

    }

    @Test
    void testNumberSchema() {

        var v = new Validator();
        var schema = v.number();


        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertFalse(schema.isValid("text"));

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));


        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));

        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
