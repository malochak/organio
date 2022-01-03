package organio.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import organio.error.domain.RequestSubError;
import organio.error.exception.InvalidRequestBodyException;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BodyValidationService {

    private final ErrorMappingService errorMappingService;

    public void checkBodyAndThrowIfNotValid(BindingResult bindingResult, String exceptionMsg) {
        if (bindingResult.hasErrors()) {
            List<RequestSubError> errors = new ArrayList<>();

            errors.addAll(errorMappingService.mapFieldsToValidationErrors(bindingResult));
            errors.addAll(errorMappingService.mapGlobalToValidationErrors(bindingResult));

            log.error("{} - {}", exceptionMsg, errors);

            throw new InvalidRequestBodyException(exceptionMsg, errors);
        }
    }
}
