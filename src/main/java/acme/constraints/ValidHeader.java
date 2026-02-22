
package acme.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Target({
	ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@NotBlank
@Length(min = 1, max = 75)
@ReportAsSingleViolation

public @interface ValidHeader {

	String message() default ""; // TODO

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
