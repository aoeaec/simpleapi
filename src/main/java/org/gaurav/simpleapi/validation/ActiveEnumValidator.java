package org.gaurav.simpleapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.gaurav.simpleapi.model.StatusType;


public class ActiveEnumValidator implements ConstraintValidator<ActiveEnumValidation, StatusType> {


    @Override
    public boolean isValid(StatusType statusType, ConstraintValidatorContext context) {
        return StatusType.Active.equals(statusType);
    }

}
