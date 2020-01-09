package residentevil.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateBeforeNowImpl implements ConstraintValidator<DateBeforeNow,LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(value==null){
            return false;
        }
        LocalDate now = LocalDate.now();
        int compareTo = now.compareTo(value);
        return compareTo>0;
    }
}
