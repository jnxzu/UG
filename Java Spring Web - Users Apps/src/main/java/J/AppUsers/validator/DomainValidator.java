package J.AppUsers.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DomainValidator implements ConstraintValidator<Domain, String> {
   public void initialize(Domain constraint) {
   }

    @Override
    public boolean isValid(String domain, ConstraintValidatorContext constraintValidatorContext) {
        return domain!=null && domain.matches("[0-9A-z]+\\.[0-9A-z]+") && domain.length()>2;
    }
}