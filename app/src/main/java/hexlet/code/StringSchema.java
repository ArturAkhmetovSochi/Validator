package hexlet.code;

import lombok.Data;
import lombok.NoArgsConstructor;

import static java.awt.SystemColor.text;

@NoArgsConstructor
@Data
public final class StringSchema {

    private Integer minimumLength = 100000;
    private String contain = "";
    private Boolean required = false;

    public StringSchema required(){
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

        if (this.required == true && o == null) {
            return false;
        } else if (this.required == true && o.toString().length() < 0) {
            return false;
        } else if (this.minimumLength < o.toString().length()) {
            return false;
        } else if ((this.contain != null) && this.contain.length() > 0 && !o.toString().contains(this.contain)) {
            return false;
        }

        return true;

    }
}

