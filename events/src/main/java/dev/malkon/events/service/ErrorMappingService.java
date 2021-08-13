package dev.malkon.events.service;

import dev.malkon.events.error.domain.RequestSubError;
import dev.malkon.events.error.domain.ValidationError;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class ErrorMappingService {


    public List<RequestSubError> mapToValidationErrors(BindingResult bindingResult) {
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
}
