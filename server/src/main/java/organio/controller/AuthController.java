package organio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import organio.domain.User;
import organio.payload.UserRegistrationBody;
import organio.service.UserService;

import javax.validation.Valid;

import static organio.constants.RequestPathConstants.API_AUTH;

@Controller
@RequestMapping(API_AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody @Valid UserRegistrationBody userRegistrationBody, BindingResult bindingResult) {
        return userService.create(userRegistrationBody, bindingResult);
    }
}
