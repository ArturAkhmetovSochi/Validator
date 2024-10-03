package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Number> {

    public NumberSchema required() {
        this.required = true;
        Predicate<Number> required = Objects::nonNull;
        predicates.put("required", required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Number> positive = x -> x == null || (int) x > 0;
        predicates.put("positive", positive);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        Predicate<Number> range = x -> x != null && (int) x >= begin && (int) x <= end;
        predicates.put("range", range);
        return this;
    }

    @Override
    public boolean isNotCorrectVariable(Object variable) {
        return !(variable instanceof Integer);
    }
}
