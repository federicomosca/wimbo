package me.askew.wimbo.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.askew.wimbo.user.domain.DTOs.LoginDTO;
import me.askew.wimbo.user.domain.DTOs.SignUpDTO;
import me.askew.wimbo.user.domain.DTOs.UserResponseDTO;
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
    public UserResponseDTO signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        return userService.signUp(signUpDTO);
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public UserResponseDTO login(@Valid @RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }
}
