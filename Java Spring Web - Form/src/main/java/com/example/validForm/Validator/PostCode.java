package com.example.validForm.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PostCodeValidator.class)
public @interface PostCode {

    String message() default "Invalid Post Code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

