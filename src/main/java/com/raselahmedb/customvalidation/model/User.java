package com.raselahmedb.customvalidation.model;

import com.raselahmedb.customvalidation.validator.FieldsValueMatch;
import com.raselahmedb.customvalidation.validator.StrongPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FieldsValueMatch.List({
	@FieldsValueMatch(
			field = "password",
			confirmField = "confirmPassword",
			message = "Password and Confirm Password must be matched!"
			)
})
public class User
{
	@NotBlank
	@Size(min = 3, max = 50)
	private String username;
	
	@NotBlank
	@Size(min = 3, max = 40)
	@StrongPassword(message = "Must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters!")
	private String password;
	
	@NotBlank
	@Size(min = 3, max = 40)
	private String confirmPassword;

}
