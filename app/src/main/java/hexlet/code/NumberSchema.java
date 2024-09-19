package hexlet.code;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Number> {

    public void required() {
        this.required = true;
        Predicate<Object> required = x -> x instanceof Integer;
        super.addPredicate(required);
    }

    public NumberSchema positive() {
        Predicate<Object> positive = x -> x instanceof Integer && (int) x > 0 || x == null;
        super.addPredicate(positive);
        return this;
    }

    public void range(int begin, int end) {
        Predicate<Object> range = x -> (Integer) x >= begin && (Integer) x <= end;
        super.addPredicate(range);
    }

    @Override
    public boolean isNotCorrectVariable(Object variable) {
        return !(variable instanceof Integer);
    }
}
