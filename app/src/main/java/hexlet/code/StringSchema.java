package hexlet.code;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class StringSchema {

    private Integer minimumLength = 100000;
    private String contain = "";
    private Boolean required = false;

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(Integer min) {
        this.minimumLength = min;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contain = substring;
        return this;
    }

    public Boolean isValid(Object o) {

        if (this.required && (o == null || o.equals(""))) {
            return false;
        } else if (o != null && o.toString().length() > minimumLength) {
            return false;
        } else if (o != null && !o.toString().contains(contain)) {
            return false;
        }

        return true;

    }
}
