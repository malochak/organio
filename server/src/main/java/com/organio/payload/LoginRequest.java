package com.organio.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.organio.utils.FieldValidationConstants.EMAIL_REQUIRED;
import static com.organio.utils.FieldValidationConstants.PASSWORD_REQUIRED;

@Data
public class LoginRequest {

    @Email
    @NotBlank(message = EMAIL_REQUIRED)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED)
    private String password;
}
