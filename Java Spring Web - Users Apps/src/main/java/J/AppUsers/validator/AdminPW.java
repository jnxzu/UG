package J.AppUsers.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdminPWValidator.class)
public @interface AdminPW {

    String message() default "Invalid Admin Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

