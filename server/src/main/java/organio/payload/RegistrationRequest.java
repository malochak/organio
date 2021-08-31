package organio.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import organio.domain.User;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collections;

@Getter
public class RegistrationRequest {

    @Email
    private String username;

    @Setter
    @Size(min = 6)
    private String password;

    private String passwordConfirmation;

    @AssertTrue
    private boolean passwordsMatch() {
        return passwordConfirmation.equals(password);
    }

    public User toUserWithEncodedPassword(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .authorities(Collections.emptyList())
                .password(passwordEncoder.encode(password))
                .build();
    }

}
