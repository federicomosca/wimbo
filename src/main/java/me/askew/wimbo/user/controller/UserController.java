package me.askew.wimbo.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.askew.wimbo.user.domain.DTOs.LoginRequest;
import me.askew.wimbo.user.domain.DTOs.SignUpRequest;
import me.askew.wimbo.user.domain.DTOs.UserResponse;
import me.askew.wimbo.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Sign up")
    @PostMapping("/sign-up")
    public UserResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
