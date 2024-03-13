package com.sannix.sanket.watchlist.entity.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriorityAnnotationLogic.class)
public @interface Priority {
	String message() default "Please enter L, M, H only";
	
	//Below lines are used to handle severity
	Class<?> []groups() default {};
	Class<? extends Payload>[] payload() default{};
}
