package organio.payload.authentication;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static organio.constants.ValidaitonConstants.NOT_BLANK;
import static organio.constants.ValidaitonConstants.NOT_VALID;

@Getter
public class LoginRequest implements AuthRequest{

    @Email(message = "E-mail address " + NOT_VALID)
    @NotBlank(message = "E-mail address " + NOT_BLANK)
    String login;

    @NotBlank(message = "Password " + NOT_BLANK)
    String password;

    @Override
    public boolean errorOnExist() {
        return false;
    }

    @Override
    public String getMessageOnExistError() {
        return "User with provided E-mail does  not exist.";
    }
}
