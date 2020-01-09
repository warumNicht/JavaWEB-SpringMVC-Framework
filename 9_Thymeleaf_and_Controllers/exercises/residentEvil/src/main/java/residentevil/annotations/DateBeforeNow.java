package residentevil.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = DateBeforeNowImpl.class)
public @interface DateBeforeNow {

    String message() default "Date, should be before the today date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
