package com.raselahmedb.customvalidation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rasel Ahmed
 */
public class FieldsMatchValueValidator implements ConstraintValidator<FieldsValueMatch, Object> {

  private String field;
  private String confirmField;

  @Override
  public void initialize(FieldsValueMatch matching) {
    this.field = matching.field();
    this.confirmField = matching.confirmField();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
    Object confirmFieldValue = new BeanWrapperImpl(value).getPropertyValue(confirmField);

    return (fieldValue != null) ? fieldValue.equals(confirmFieldValue) : confirmFieldValue == null;
  }
}