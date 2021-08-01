package dev.malkon.events.domain

import spock.lang.Specification

class EventTest extends Specification {

    def 'event should be valid after creation'() {
        def eventBuilder = Event.builder().id('123').name('Event').description('Event\'s description')

        when:
        def event = eventBuilder.build()

        then:
        event.getId() == '123'
        event.getName() == 'Event'
        event.getDescription() == 'Event\'s description'
        event == new Event('123', 'Event', 'Event\'s description')
    }
}
