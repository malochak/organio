package organio.payload;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {

    @Email
    String username;

    @NotBlank
    String password;
}
