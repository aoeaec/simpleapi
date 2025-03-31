package org.gaurav.simpleapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

public class DateValidator implements ConstraintValidator<DateValidation, Date>
{
    public boolean isValid(Date date, ConstraintValidatorContext cxt) {
        return date.after(new Date());
    }
}
