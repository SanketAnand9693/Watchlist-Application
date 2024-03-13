package com.sannix.sanket.watchlist.entity.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RatingAnnotationLogic.class)
public @interface Rating {
String message() default "Please enter B/W 5 to 10";
	
	//Below lines are used to handle severity
	Class<?> []groups() default {};
	Class<? extends Payload>[] payload() default{};
}
