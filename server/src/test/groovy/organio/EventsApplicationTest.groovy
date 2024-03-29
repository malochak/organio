package organio

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import organio.repository.EventRepository
import spock.lang.Specification

@SpringBootTest
class EventsApplicationTest extends Specification {

    @Autowired
    ApplicationContext context

    @Autowired
    EventRepository eventRepository

    def 'should load spring context'() {
        expect:
        context != null
        eventRepository != null
    }

}
