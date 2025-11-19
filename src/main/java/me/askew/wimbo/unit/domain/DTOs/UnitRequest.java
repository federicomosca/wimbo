package me.askew.wimbo.unit.domain.DTOs;

import jakarta.validation.constraints.NotBlank;

public record UnitRequest(

        @NotBlank(message = "Friendly name is required")
        String friendlyName,
        String address,
        String description,
        Long userId
) {
}
