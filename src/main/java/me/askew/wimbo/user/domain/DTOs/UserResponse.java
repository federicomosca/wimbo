package me.askew.wimbo.user.domain.DTOs;

public record UserResponse(
        Long id,
        String username,
        String email
) {
}
