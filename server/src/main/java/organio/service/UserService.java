package organio.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import organio.domain.User;
import organio.error.exception.UserExistsException;
import organio.payload.UserRegistrationBody;
import organio.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final BodyValidationService bodyValidationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findUserByUsername(username);

        user.ifPresent(it -> log.error("Cannot find user with username : " + username));

        return user.orElseThrow(() -> new UsernameNotFoundException("Cannot find user with username : " + username));
    }

    public User create(UserRegistrationBody userRegistrationBody, BindingResult bindingResult) {
        Optional<User> user = repository.findUserByUsername(userRegistrationBody.getUsername());

        if (user.isPresent()) {
            throw new UserExistsException("User already exists.");
        }

        bodyValidationService.checkBodyAndThrowIfNotValid(bindingResult, "Register Request Body is INVALID");

        User savedUser = repository.save(userRegistrationBody.toUserWithEncodedPassword(passwordEncoder));

        log.info("Username with email address {} has been saved.", savedUser.getUsername());

        return savedUser;
    }


}
