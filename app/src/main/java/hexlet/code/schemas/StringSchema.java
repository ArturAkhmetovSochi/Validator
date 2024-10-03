package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        this.required = true;
        Predicate<String> isRequired = x -> x != null && !x.isEmpty();
        predicates.put("isRequired", isRequired);
        return this;
    }

    public StringSchema minLength(Integer min) {
        Predicate<String> minLength = x -> x.length() >= min;
        predicates.put("minLength", minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> contain = x -> x.contains(substring);
        predicates.put("contain", contain);
        return this;
    }

    @Override
    public boolean isNotCorrectVariable(Object variable) {
        return !(variable instanceof String) || ((String) variable).isEmpty();
    }
}



