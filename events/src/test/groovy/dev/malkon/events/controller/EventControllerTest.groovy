package dev.malkon.events.controller


import dev.malkon.events.helper.EventCreationHelper
import dev.malkon.events.service.EventService
import org.apache.commons.collections4.ListUtils
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import spock.lang.Specification

class EventControllerTest extends Specification {

    def service = Mock(EventService.class)
    def controller = new EventController(service)


    def 'should return event with response code 201'() {
        given:
        def event = EventCreationHelper.validEvent
        def bindingResult = Mock(BindingResult)

        when:
        def result = controller.createOrUpdate(event, bindingResult)

        then:
        result != null
        1 * service.save(event, bindingResult) >> event
        result.getStatusCode() == HttpStatus.CREATED
        result.getHeaders().get("Location") == [event.toURI().toString()]
    }

    def 'should return event when id in path is given'() {
        given:
        def event = EventCreationHelper.validEvent

        when:
        def result = controller.findById("VALID_ID")

        then:
        1 * service.finById("VALID_ID") >> event
        result.getStatusCode() == HttpStatus.FOUND
        result.getBody() == event
    }

    def 'should return list of events when findAll is called'() {
        given:
        def events = EventCreationHelper.validEvents

        when:
        def result = controller.findAll()

        then:
        1 * service.findAll() >> events
        result.getStatusCode() == HttpStatus.FOUND
        !result.getBody().isEmpty()
        ListUtils.isEqualList(result.getBody(), events)

    }

}
