package organio.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import organio.error.domain.RequestSubError;
import organio.error.domain.ValidationError;

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
