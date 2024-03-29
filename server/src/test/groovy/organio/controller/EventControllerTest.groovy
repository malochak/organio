package organio.controller


import org.springframework.validation.BindingResult
import organio.helper.EventCreationHelper
import organio.service.EventService
import spock.lang.Specification

class EventControllerTest extends Specification {

    def service = Mock(EventService.class)
    def controller = new EventController(service)


    def 'should call save on EventService object'() {
        given:
        def event = EventCreationHelper.validEvent
        def bindingResult = Mock(BindingResult)

        when:
        def result = controller.create(event, bindingResult)

        then:
        1 * service.save(event, bindingResult) >> event
        result != null
    }

    def 'should call update on EventService object'() {
        given:
        def event = EventCreationHelper.validEvent
        def bindingResult = Mock(BindingResult)

        when:
        def result = controller.update(event.id, event, bindingResult)

        then:
        1 * service.update(event.id, event, bindingResult) >> event
        result != null
    }
    def 'should call findById on EventService object'() {
        given:
        def event = EventCreationHelper.validEvent

        when:
        def result = controller.findById("VALID_ID")

        then:
        1 * service.finById("VALID_ID") >> event
        result != null
    }

    def 'should return list of events when findAll is called'() {
        given:
        def events = EventCreationHelper.validEvents

        when:
        def result = controller.findAll()

        then:
        1 * service.findAll() >> events
        result != null
        result.size() == events.size()
    }

}
