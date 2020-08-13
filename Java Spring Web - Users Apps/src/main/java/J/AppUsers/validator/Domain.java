package J.AppUsers.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DomainValidator.class)
public @interface Domain {

    String message() default "Invalid Domain\n";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

