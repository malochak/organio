package organio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import organio.payload.TokenResponse;
import organio.payload.authentication.LoginRequest;
import organio.payload.authentication.RegistrationRequest;
import organio.service.AuthService;

import javax.validation.Valid;

import static organio.constants.RequestPathConstants.API_AUTH;

@Controller
@RequestMapping(API_AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult bindingResult) {
        authService.create(registrationRequest, bindingResult);
    }

    @ResponseBody
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        return authService.authenticate(loginRequest, bindingResult);
    }
}
