package org.gaurav.simpleapi.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class DateValidatorTest {

    DateValidator dateValidator = new DateValidator();

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @ParameterizedTest
    @MethodSource("getDateValidatorInput")
    void testDateValidator(Date date, boolean expectedResult) {
        assertEquals(dateValidator.isValid(date, constraintValidatorContext), expectedResult);
    }

    private static Stream<Arguments> getDateValidatorInput() {
        return Stream.of(
                Arguments.of(getDate("11-11-2052 12:24"), true),
                Arguments.of(getDate("11-11-2019 12:24"), false)
        );
    }
    private static Date getDate(String inputString) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date inputDate;
        try {
            inputDate = dateFormat.parse(inputString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return inputDate;
    }
}