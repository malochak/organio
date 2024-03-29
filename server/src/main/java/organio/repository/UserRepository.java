package organio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import organio.domain.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByUsername(String username);

}
