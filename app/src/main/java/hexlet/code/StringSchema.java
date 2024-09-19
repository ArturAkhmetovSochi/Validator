package hexlet.code;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public void required() {
        this.required = true;
        Predicate<Object> isRequired = x -> x instanceof String && !x.equals("");
        super.addPredicate(isRequired);
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

