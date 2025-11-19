package me.askew.wimbo.user.domain.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    String email,

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    String username,

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    String password,

    @NotBlank(message = "Confirm Password is required")
    String confirmPassword
) {}





