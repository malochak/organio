package organio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import organio.domain.Event;

public interface EventRepository extends MongoRepository<Event, String> {

}
