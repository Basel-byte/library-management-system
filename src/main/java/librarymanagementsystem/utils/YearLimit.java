package librarymanagementsystem.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearLimitValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface YearLimit {
    String message() default "{year.limit.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}