package organio.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import organio.error.domain.RequestSubError;
import organio.error.domain.ValidationError;
import organio.payload.authentication.RegistrationRequest;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class ErrorMappingService {

    public List<RequestSubError> mapFieldsToValidationErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(error ->
                        (RequestSubError) new ValidationError(
                                error.getObjectName(),
                                error.getField(),
                                error.getRejectedValue(),
                                error.getDefaultMessage()
                        )
                ).toList();
    }

    public List<RequestSubError> mapGlobalToValidationErrors(BindingResult bindingResult) {
        return bindingResult.getGlobalErrors().stream()
                .map(error ->
                        (RequestSubError) new ValidationError(
                                error.getObjectName(),
                                evalFieldNameForGlobalError(error),
                                Strings.EMPTY,
                                error.getDefaultMessage()
                        )).toList();
    }

    /**
     * It's needed to dig into ObjectError arguments,
     * because there is stored annotation property that is created for type validations.
     * I usually don't put a doc comments, this is an exception as I needed to explain what the heck is happening here.
     *
     * @param error obj that contains type validation error
     * @return fieldName set when using PasswordMatching annotation
     * @see organio.validator.PasswordMatching Annotation definition
     * @see RegistrationRequest Annotation property setting
     */
    private String evalFieldNameForGlobalError(ObjectError error) {
        Object[] errorArgs = error.getArguments();
        if (nonNull(errorArgs) && errorArgs.length > 0) {
            return errorArgs[1].toString();
        }
        return "unknown";
    }

}
