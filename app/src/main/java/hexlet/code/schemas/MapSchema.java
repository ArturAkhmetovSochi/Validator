package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema<T> extends BaseSchema<Map<String, String>> {

    public MapSchema<T> required() {
        this.required = true;
        Predicate<Map<String, String>> required = Objects::nonNull;
        predicates.put("required", required);
        return this;
    }

    public MapSchema<T> sizeof(Integer number) {
        Predicate<Map<String, String>> sizeof = x -> x.size() == number;
        predicates.put("sizeof", sizeof);
        return this;
    }

    @Override
    boolean isNotCorrectVariable(Object o) {
        return !(o instanceof Map<?, ?>);
    }

    public MapSchema<T> shape(Map<String, BaseSchema> schemas) {
        Predicate<Map<String, String>> shape = x -> checkData(schemas, x);
        predicates.put("shape", shape);
        return this;
    }

    private boolean checkData(Map<String, BaseSchema> schemas, Map<?, ?> map) {
        for (Map.Entry<String, BaseSchema> entry: schemas.entrySet()) {
            String key = entry.getKey();
            if (!map.containsKey(key) || !entry.getValue().isValid(map.get(key))) {
                return false;
            }
        }
        return true;
    }




}
