package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Boolean required = false;
    protected List<Predicate<T>> predicates = new ArrayList<>();

    public void addPredicate(Predicate<T> test) {
        predicates.add(test);
    }

    public boolean isValid(T t) {
        if (!this.required && !isNotCorrectVariable(t)) {
            for (Predicate<T> check : predicates) {
                if (check.test(t)) {
                    return true;
                }
            }
        }

        for (Predicate<T> check : predicates) {
            if (!check.test(t)) {
                return false;
            }
        }

        return true;

    }

    abstract boolean isNotCorrectVariable(Object o);

}
