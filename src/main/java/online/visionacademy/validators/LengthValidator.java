package online.visionacademy.validators;

import online.visionacademy.exceptions.ValidationException;

public class LengthValidator implements Validators {

    private String field;
    private String value;
    private int min;
    private int max;

    private String errorMessage;


    public LengthValidator(String field, String value, int min, int max) {
            setValues(field, value, min, max);
    }

    public void setValues(String field, String value, int min, int max){
        this.field = field;
        this.value = value;
        this.min = min;
        this.max = max;
        setErrorMessage(field +" length must be between "+min+" ~ "+max);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void validate() {

        if (min > field.length() || field.length() > max)
            throw new ValidationException(errorMessage);
    }
}
