package org.gaurav.simpleapi.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.gaurav.simpleapi.model.StatusType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ActiveEnumValidatorTest {


    ActiveEnumValidator validator = new ActiveEnumValidator();

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @ParameterizedTest
    @MethodSource("getEnumValidatorInput")
    void testEnumValidator(StatusType statusType, boolean expectedResult) {
        assertEquals(validator.isValid(statusType, constraintValidatorContext), expectedResult);
    }

    private static Stream<Arguments> getEnumValidatorInput() {
        return Stream.of(
                Arguments.of(StatusType.Active, true),
                Arguments.of(StatusType.Inactive, false)
        );
    }
}