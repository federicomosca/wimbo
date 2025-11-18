package me.askew.wimbo.user.service;

import me.askew.wimbo.user.domain.DTOs.LoginDTO;
import me.askew.wimbo.user.domain.DTOs.SignUpDTO;
import me.askew.wimbo.user.domain.DTOs.UserResponseDTO;

public interface UserService {

    UserResponseDTO signUp(SignUpDTO signUpDTO);
    UserResponseDTO login(LoginDTO loginDTO);
}
