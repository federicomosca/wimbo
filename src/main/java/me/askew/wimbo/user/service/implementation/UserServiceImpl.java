package me.askew.wimbo.user.service.implementation;

import lombok.RequiredArgsConstructor;
import me.askew.wimbo.user.domain.DTOs.LoginRequest;
import me.askew.wimbo.user.domain.DTOs.SignUpRequest;
import me.askew.wimbo.user.domain.DTOs.UserResponse;
import me.askew.wimbo.user.domain.model.User;
import me.askew.wimbo.user.repository.UserRepository;
import me.askew.wimbo.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse signUp(SignUpRequest signUpRequest) {

        //check password match
        if (!signUpRequest.password().equals(signUpRequest.confirmPassword()))
            throw new IllegalArgumentException("Passwords do not match");

        //unique constraints
        if(userRepository.existsByEmail(signUpRequest.email()))
            throw new IllegalArgumentException("Email already exists");

        if (userRepository.existsByUsername(signUpRequest.username()))
            throw new IllegalArgumentException("Username already exists");

        //map dto -> entity
        User user = new User();
        user.setEmail(signUpRequest.email());
        user.setUsername(signUpRequest.username());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));

        //save
        User saved = userRepository.save(user);

        //return safe response
        return new UserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(()->new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword()))
            throw new IllegalArgumentException("Invalid credentials");

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}