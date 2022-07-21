package online.visionacademy.validators;

import online.visionacademy.exceptions.ValidationException;

public class NullValidator implements Validators{

    private String field;
    private Object object;

    public NullValidator(String field, Object object){

        this.field = field;
        this.object = object;

    }


    @Override
    public void validate() {
        if (object == null)
            throw new ValidationException(field + " cant be null \n");
    }
}
