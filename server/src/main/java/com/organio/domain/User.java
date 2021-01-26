package com.organio.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank(message = "Email field is required")
    private String email;

    @NotBlank(message = "Password field is required")
    private String password;

    @Transient
    private String confirmPassword;
}
