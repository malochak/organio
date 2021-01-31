package com.organio.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.organio.utils.FieldValidationConstants.*;

@Data
public class RegisterRequest {
    @NotBlank(message = NAME_REQUIRED)
    private String name;

    @Email
    @NotBlank(message = EMAIL_REQUIRED)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED)
    private String password;
}
