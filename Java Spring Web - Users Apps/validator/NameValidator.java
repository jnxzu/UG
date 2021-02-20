package J.AppUsers.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {
   public void initialize(Name constraint) {
   }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return name!=null && name.length()<21 && name.length()>2;
    }
}