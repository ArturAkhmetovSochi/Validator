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
}
