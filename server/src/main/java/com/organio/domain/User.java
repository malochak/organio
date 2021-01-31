package com.organio.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.organio.utils.FieldValidationConstants.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank( message = NAME_REQUIRED)
    private String name;

    @Email
    @NotBlank(message = EMAIL_REQUIRED)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED)
    private String password;

    @Transient
    private String confirmPassword;

    private String imageUrl;

    @NotNull
    private Boolean emailVerified = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;
}
