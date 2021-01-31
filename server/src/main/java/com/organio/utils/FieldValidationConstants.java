package com.organio.utils;

public class FieldValidationConstants {

    public static final String NAME_REQUIRED = "Name field is required";
    public static final String EMAIL_REQUIRED = "Email field is required";
    public static final String PASSWORD_REQUIRED = "Password field is required";

    private FieldValidationConstants() {
        throw new AssertionError(
                "Unauthorized access to constructor in class: "
                        + this.getClass().getName()
        );
    }

}
