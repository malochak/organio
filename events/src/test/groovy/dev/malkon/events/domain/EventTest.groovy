package dev.malkon.events.domain


import spock.lang.Specification

import java.time.LocalDateTime

import static dev.malkon.events.constants.RequestPathConstants.API_EVENT

class EventTest extends Specification {

    def 'event should be valid after creation'() {
        given:
        def now = LocalDateTime.now().toDate()
        def eventBuilder = Event.builder()
                .id('123')
                .name('Event')
                .description('Event\'s description')
                .date(now)
                .span(900 * 1000)

        when:
        def event = eventBuilder.build()

        then:
        event.getId() == '123'
        event.getName() == 'Event'
        event.getDescription() == 'Event\'s description'
        event.getDate() == now
        event.getSpan() == 900 * 1000
        event.toURI() == URI.create(API_EVENT + "/123")
    }
}
