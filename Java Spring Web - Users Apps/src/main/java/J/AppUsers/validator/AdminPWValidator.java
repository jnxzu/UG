package J.AppUsers.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdminPWValidator implements ConstraintValidator<AdminPW, String> {
   public void initialize(AdminPW constraint) {
   }

    @Override
    public boolean isValid(String adminPW, ConstraintValidatorContext constraintValidatorContext) {
        return adminPW.equals("lmao");
    }
}