package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Boolean required = false;
    protected Map<String, Predicate<T>> predicates = new HashMap<>();


    public boolean isValid(T t) {
        if (!this.required && !isNotCorrectVariable(t)) {
            for (Predicate<T> check : predicates.values()) {
                if (check.test(t)) {
                    return true;
                }
            }
        }

        for (Predicate<T> check : predicates.values()) {
            if (!check.test(t)) {
                return false;
            }
        }

        return true;

    }

    abstract boolean isNotCorrectVariable(Object o);

}
