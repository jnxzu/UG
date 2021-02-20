package J.AppUsers.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
   public void initialize(Password constraint) {
   }

    @Override
    public boolean isValid(String pw, ConstraintValidatorContext constraintValidatorContext) {
        return pw!=null && pw.matches("[0-9A-z]*[0-9][0-9A-z]*[A-Z][0-9A-z]*[a-z][0-9A-z]*") && pw.length()>6;
    }
}