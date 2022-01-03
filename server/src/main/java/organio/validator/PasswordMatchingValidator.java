package organio.validator;

import organio.payload.RegistrationRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, RegistrationRequest> {

    @Override
    public boolean isValid(RegistrationRequest registrationRequest, ConstraintValidatorContext constraintValidatorContext) {
        return registrationRequest.getPassword().equals(registrationRequest.getPasswordConfirmation());
    }
}
