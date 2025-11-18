package me.askew.wimbo.user.service.implementation;

import lombok.RequiredArgsConstructor;
import me.askew.wimbo.user.domain.DTOs.LoginDTO;
import me.askew.wimbo.user.domain.DTOs.SignUpDTO;
import me.askew.wimbo.user.domain.DTOs.UserResponseDTO;
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
    public UserResponseDTO signUp(SignUpDTO signUpDTO) {

        //check password match
        if (!signUpDTO.password().equals(signUpDTO.confirmPassword()))
            throw new IllegalArgumentException("Passwords do not match");

        //unique constraints
        if(userRepository.existsByEmail(signUpDTO.email()))
            throw new IllegalArgumentException("Email already exists");

        if (userRepository.existsByUsername(signUpDTO.username()))
            throw new IllegalArgumentException("Username already exists");

        //map dto -> entity
        User user = new User();
        user.setEmail(signUpDTO.email());
        user.setUsername(signUpDTO.username());
        user.setPassword(passwordEncoder.encode(signUpDTO.password()));

        //save
        User saved = userRepository.save(user);

        //return safe response
        return new UserResponseDTO(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.username())
                .orElseThrow(()->new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(loginDTO.password(), user.getPassword()))
            throw new IllegalArgumentException("Invalid credentials");

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}