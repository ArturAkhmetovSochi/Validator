package hexlet.code.schema;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map> {

    public void required() {
        this.required = true;
        Predicate<Object> required = x -> x instanceof Map<?, ?>;
        super.addPredicate(required);
    }

    public void sizeof(Integer number) {
        Predicate<Object> sizeof = x -> x instanceof Map<?, ?> && ((Map<?, ?>) x).size() == number;
        super.addPredicate(sizeof);
    }

    @Override
    boolean isNotCorrectVariable(Object o) {
        return !(o instanceof Map<?, ?>);
    }
}
