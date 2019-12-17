package com.example.validForm.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SalaryValidator implements ConstraintValidator<Salary, Integer> {
   public void initialize(Salary constraint) {
   }

    @Override
    public boolean isValid(Integer salary, ConstraintValidatorContext constraintValidatorContext) {
        return salary>=2000 && salary<=3000;
    }
}