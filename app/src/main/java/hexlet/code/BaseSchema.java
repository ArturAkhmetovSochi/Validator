package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Boolean required = false;
    private final List<Predicate<Object>> predicates = new ArrayList<>();

    public void addPredicate(Predicate predicate) {
        predicates.add(predicate);

    }

    public boolean isValid(Object o) {
        if (!this.required && !isNotCorrectVariable(o)) {
            return true;
        }

        for (Predicate predicate : predicates) {
            if (!predicate.test(o)) {
                return false;
            }
        }

        return true;

    }

    abstract boolean isNotCorrectVariable(Object o);

}
