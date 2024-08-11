package librarymanagementsystem.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearLimitValidator implements ConstraintValidator<YearLimit, Integer> {

    @Override
    public void initialize(YearLimit constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        int currentYear = Year.now().getValue();
        if (year <= currentYear)
            return true;
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Year must be less than or equal to " + Year.now().getValue())
                .addConstraintViolation();
        return false;
    }
}
