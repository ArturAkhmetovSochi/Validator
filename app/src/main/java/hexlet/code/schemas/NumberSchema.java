package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Number> {

    public NumberSchema required() {
        this.required = true;
        Predicate<Number> required = Objects::nonNull;
        addPredicate(required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Number> positive = x -> x == null || (int) x > 0;
        addPredicate(positive);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        Predicate<Number> range = x -> (int) x >= begin && (int) x <= end;
        addPredicate(range);
        return this;
    }

    @Override
    public boolean isNotCorrectVariable(Object variable) {
        return !(variable instanceof Integer);
    }
}
