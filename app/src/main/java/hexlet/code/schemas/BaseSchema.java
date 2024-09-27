package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Boolean required = false;
    private final List<Predicate<Object>> predicates = new ArrayList<>();

    public void addPredicate(Predicate<Object> test) {
        predicates.add(test);
    }

    public boolean isValid(Object o) {
        if (!this.required && !isNotCorrectVariable(o)) {
            for (Predicate<Object> check : predicates) {
                if (check.test(o)) {
                    return true;
                }
            }
        }

        for (Predicate<Object> check : predicates) {
            if (!check.test(o)) {
                return false;
            }
        }

        return true;

    }

    abstract boolean isNotCorrectVariable(Object o);

}
