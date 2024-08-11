package librarymanagementsystem.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.sql.Date;
import java.time.temporal.ChronoUnit;

public class BorrowingDaysLimitValidator implements ConstraintValidator<BorrowingDaysLimit, Object> {
    private long days;
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(BorrowingDaysLimit constraintAnnotation) {
        days = constraintAnnotation.days();
        field = constraintAnnotation.field();
        fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(o);
        Date borrowDate = (Date) beanWrapper.getPropertyValue(field);
        Date returnDate = (Date) beanWrapper.getPropertyValue(fieldMatch);
        if (borrowDate == null || returnDate == null)
            throw new IllegalArgumentException("Date cannot be null!");

        long days = ChronoUnit.DAYS.between(borrowDate.toLocalDate(), returnDate.toLocalDate());
        if (days <= this.days)
            return true;
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Borrowing duration must be less than or equal to " + days)
                .addConstraintViolation();
        return false;
    }
}
