package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        this.required = true;
        Predicate<Object> isRequired = x -> x instanceof String && !((String) x).isEmpty();
        super.addPredicate(isRequired);
        return this;
    }

    public StringSchema minLength(Integer min) {
        Predicate<Object> minLength = x -> x.toString().length() >= min;
        super.addPredicate(minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<Object> contain = x -> x.toString().contains(substring);
        super.addPredicate(contain);
        return this;
    }

    @Override
    public boolean isNotCorrectVariable(Object variable) {
        return !(variable instanceof String) || ((String) variable).isEmpty();
    }
}



