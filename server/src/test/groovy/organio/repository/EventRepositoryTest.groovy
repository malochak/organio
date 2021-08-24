package organio.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import organio.domain.Event
import spock.lang.Specification

@SpringBootTest
class EventRepositoryTest extends Specification {

    @Autowired
    EventRepository repository

    def setup() {
        repository.deleteAll()
    }

    def 'should add elements to repository'() {
        when:
        def result = repository.save(event)

        then:
        repository.findAll().size() == 1
        result.getId() != null
        repository.findById(result.getId()).isPresent()
    }

    def 'should update element'() {
        given:
        def savedEvent = repository.save(event)
        def updatedEvent = Event.builder().id(savedEvent.getId()).name('Updated name').build()

        when:
        def result = repository.save(updatedEvent)

        then:
        result.getId() == savedEvent.getId()
        result.getName() == 'Updated name'
    }

    def 'should delete event by id'() {
        given:
        def savedEvent = repository.save(event)

        when:
        repository.deleteById(savedEvent.getId())

        then:
        repository.findAll().size() == 0
    }

    def event = Event.builder()
            .name('Event')
            .description('Event description')
            .build()
}
