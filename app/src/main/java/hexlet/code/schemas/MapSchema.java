package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        this.required = true;
        Predicate<Object> required = x -> x instanceof Map<?, ?>;
        super.addPredicate(required);
        return this;
    }

    public MapSchema sizeof(Integer number) {
        Predicate<Object> sizeof = x -> x instanceof Map<?, ?> && ((Map<?, ?>) x).size() == number;
        super.addPredicate(sizeof);
        return this;
    }

    @Override
    boolean isNotCorrectVariable(Object o) {
        return !(o instanceof Map<?, ?>);
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        Predicate<Object> shape = x -> checkData(schemas, (Map<?, ?>) x);
        super.addPredicate(shape);
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
