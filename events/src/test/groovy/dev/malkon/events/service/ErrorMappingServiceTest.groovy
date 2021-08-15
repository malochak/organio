package dev.malkon.events.service


import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import spock.lang.Specification

class ErrorMappingServiceTest extends Specification {
    def bindingResult = Mock(BindingResult)
    def errorMappingService = new ErrorMappingService()

    def 'should properly map binding result to list of RequestSubError'() {
        given:
        def fieldErrors = [
                new FieldError(
                        'event',
                        'name',
                        '',
                        true, null, null,
                        'Event\'s name cannot be blank.')
        ]
        when:
        def result = errorMappingService.mapToValidationErrors(bindingResult)

        then:
        1 * bindingResult.getFieldErrors() >> fieldErrors
        result != null
    }
}
