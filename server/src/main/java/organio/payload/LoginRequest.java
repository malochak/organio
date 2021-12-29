package organio.payload;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static organio.constants.ValidaitonConstants.NOT_BLANK;
import static organio.constants.ValidaitonConstants.NOT_VALID;

@Getter
public class LoginRequest {

    @Email(message = "E-mail address " + NOT_VALID)
    @NotBlank(message = "E-mail address " + NOT_BLANK)
    String login;

    @NotBlank(message = "Password " + NOT_BLANK)
    String password;
}
