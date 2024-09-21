package hexlet.code.schema;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        this.required = true;
        Predicate<Object> required = x -> x instanceof Integer;
        super.addPredicate(required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positive = x -> x instanceof Integer && (int) x > 0 || x == null;
        super.addPredicate(positive);
        return this;
    }

    public BaseSchema range(int begin, int end) {
        Predicate<Object> range = x -> (Integer) x >= begin && (Integer) x <= end;
        super.addPredicate(range);
        return this;
    }

    @Override
    public boolean isNotCorrectVariable(Object variable) {
        return !(variable instanceof Integer);
    }
}
