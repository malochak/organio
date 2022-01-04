package organio.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import organio.domain.User;
import organio.error.exception.UserExistsException;
import organio.payload.LoginRequest;
import organio.payload.RegistrationRequest;
import organio.payload.TokenResponse;
import organio.repository.UserRepository;
import organio.security.jwt.TokenProvider;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final TokenProvider tokenProvider;
    private final BodyValidationService bodyValidationService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public void create(RegistrationRequest registrationRequest, BindingResult bindingResult) {
        Optional<User> user = repository.findUserByUsername(registrationRequest.getLogin());

        if (user.isPresent()) {
            throw new UserExistsException("User already exists.");
        }

        bodyValidationService.checkBodyAndThrowIfNotValid(bindingResult, "Register Request Body is INVALID");

        User savedUser = repository.save(registrationRequest.toUserWithEncodedPassword(passwordEncoder));

        log.info("Username with email address {} has been saved.", savedUser.getUsername());
    }

    public TokenResponse authenticate(LoginRequest loginRequest, BindingResult bindingResult) {
        bodyValidationService.checkBodyAndThrowIfNotValid(bindingResult, "Login Request Body is INVALID");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return TokenResponse.create(token);
    }
}
