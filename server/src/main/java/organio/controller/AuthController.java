package organio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import organio.domain.User;
import organio.payload.LoginRequest;
import organio.payload.RegistrationRequest;
import organio.payload.TokenResponse;
import organio.service.AuthService;

import javax.validation.Valid;

import static organio.constants.RequestPathConstants.API_AUTH;

@Controller
@RequestMapping(API_AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ResponseBody
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult bindingResult) {
        return authService.create(registrationRequest, bindingResult);
    }

    @ResponseBody
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        return authService.authenticate(loginRequest, bindingResult);
    }
}
