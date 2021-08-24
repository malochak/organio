package organio.service

import org.springframework.validation.BindingResult
import organio.domain.Event
import organio.error.domain.ValidationError
import organio.error.exception.InvalidRequestBodyException
import organio.error.exception.RecordCreationException
import organio.error.exception.RecordNotFoundException
import organio.repository.EventRepository
import spock.lang.Specification

import static organio.helper.EventCreationHelper.*

class EventServiceTest extends Specification {

    def repository = Mock(EventRepository)
    def errorMappingService = Mock(ErrorMappingService)
    def bindingResult = Mock(BindingResult)

    def service = new EventService(repository, errorMappingService)

    def "should create event - body valid"() {
        given:
        def event = eventWithoutId

        when:
        def result = service.save(event, bindingResult)

        then:
        1 * repository.save(event) >> validEvent
        1 * bindingResult.hasErrors() >> false
        0 * errorMappingService.mapToValidationErrors(bindingResult)
        result != null
    }

    def 'should throw RecordCreationException on event creation - predefined id'() {
        given:
        def event = validEvent

        when:
        service.save(event, bindingResult)

        then:
        thrown(RecordCreationException)
        0 * bindingResult.hasErrors()
        0 * errorMappingService.mapToValidationErrors(bindingResult)
        0 * repository.save(_ as Event)
    }

    def 'should throw InvalidRequestBodyException on event creation - invalidFields'() {
        given:
        def event = eventWithoutId

        when:
        def result = service.save(event, bindingResult)

        then:
        thrown(InvalidRequestBodyException)
        1 * bindingResult.hasErrors() >> true
        1 * errorMappingService.mapToValidationErrors(bindingResult) >> [
                new ValidationError("event",
                        "name", "", "Event's name cannot be blank.")
        ]
        0 * repository.save(_ as Event)
    }

    def 'should update existing event - body valid'() {
        given:
        def event = validEvent

        when:
        def result = service.update(event.id, event, bindingResult)

        then:
        1 * bindingResult.hasErrors() >> false
        0 * errorMappingService.mapToValidationErrors(bindingResult)
        1 * repository.findById(event.id) >> Optional.of(event)
        1 * repository.save(event) >> event
        result != null
    }

    def 'should throw InvalidRequestBodyException on event update - wrong event\'s body'() {
        given:
        def event = validEvent

        when:
        service.update(event.id, event, bindingResult)

        then:
        thrown(InvalidRequestBodyException)
        1 * bindingResult.hasErrors() >> true
        1 * errorMappingService.mapToValidationErrors(bindingResult) >> [
                new ValidationError("event",
                        "name", "", "Event's name cannot be blank.")
        ]
        0 * repository.findById(_ as String)
        0 * repository.save(_ as Event)
    }

    def 'should throw InvalidRequestBodyException on event update - path id don\'t match id from body'() {
        given:
        def event = validEvent

        when:
        service.update("not_matching_id", event, bindingResult)

        then:
        thrown(InvalidRequestBodyException)
        1 * bindingResult.hasErrors() >> false
        0 * errorMappingService.mapToValidationErrors(bindingResult)
        0 * repository.findById(_ as String)
        0 * repository.save(_ as Event)
    }

    def 'should throw RecordNotFound on event update - event not found in DB'() {
        given:
        def event = validEvent

        when:
        service.update(event.id, event, bindingResult)

        then:
        thrown(RecordNotFoundException)
        1 * bindingResult.hasErrors() >> false
        0 * errorMappingService.mapToValidationErrors(bindingResult)
        1 * repository.findById(event.id) >> Optional.empty()
        0 * repository.save(event)
    }

    def 'should find event by id - event exists'() {
        given:
        def id = "123"

        when:
        def result = service.finById(id)

        then:
        1 * repository.findById(id) >> Optional.of(validEvent)
        result != null
    }

    def 'should throw RecordNotFound on event search - event not found in DB'(){
        given:
        def id = "not_existing_id"

        when:
        service.finById(id)

        then:
        thrown(RecordNotFoundException)
        1 * repository.findById(id) >> Optional.empty()
    }

    def "should find all events in DB"() {
        when:
        def result = service.findAll()

        then:
        1 * repository.findAll() >> validEvents
        result != null
        result.size() == 3
    }


}
