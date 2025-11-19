package me.askew.wimbo.user.service;

import me.askew.wimbo.user.domain.DTOs.LoginRequest;
import me.askew.wimbo.user.domain.DTOs.SignUpRequest;
import me.askew.wimbo.user.domain.DTOs.UserResponse;

public interface UserService {

    UserResponse signUp(SignUpRequest signUpRequest);
    UserResponse login(LoginRequest loginRequest);
}
