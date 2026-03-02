
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
	ElementType.FIELD, ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@NotBlank
@ReportAsSingleViolation

@Length(min = 1, max = 255)

public @interface ValidText {

	String message() default "{acme.validation.invalid-text}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
