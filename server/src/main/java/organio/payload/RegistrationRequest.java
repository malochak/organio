package organio.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import organio.domain.User;
import organio.validator.PasswordMatching;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;

import static organio.constants.ValidaitonConstants.NOT_BLANK;
import static organio.constants.ValidaitonConstants.NOT_VALID;

@Getter
@PasswordMatching(fieldName = "passwordConfirmation")
public class RegistrationRequest {

    @Email(message = "E-mail address " + NOT_VALID)
    @NotBlank(message = "E-mail address " + NOT_BLANK)
    private String login;

    @Setter
    @Size(min = 6, message = "Password should contain at least 6 characters.")
    private String password;

    private String passwordConfirmation;

    public User toUserWithEncodedPassword(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(login)
                .authorities(Collections.emptyList())
                .password(passwordEncoder.encode(password))
                .build();
    }

}
