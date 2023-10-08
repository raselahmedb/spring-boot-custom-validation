package com.raselahmedb.customvalidation.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * @author Rasel Ahmed
 */
@Constraint(validatedBy = FieldsMatchValueValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {
  String field();

  String confirmField();

  String message() default "Fields values don't match!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Target({ ElementType.TYPE })
  @Retention(RetentionPolicy.RUNTIME)
  @interface List {
    FieldsValueMatch[] value();
  }
}