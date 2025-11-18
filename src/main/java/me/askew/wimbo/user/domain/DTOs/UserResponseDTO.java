package me.askew.wimbo.user.domain.DTOs;

public record UserResponseDTO(
        Long id,
        String username,
        String email
) {
}
