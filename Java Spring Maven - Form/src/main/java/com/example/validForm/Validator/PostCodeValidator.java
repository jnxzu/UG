package com.example.validForm.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostCodeValidator implements ConstraintValidator<PostCode, String> {
   public void initialize(PostCode constraint) {
   }

    @Override
    public boolean isValid(String postcode, ConstraintValidatorContext constraintValidatorContext) {
        return postcode!=null && postcode.matches("[0-9]{2}-[0-9]{3}");
    }
}