package me.askew.wimbo.unit.domain.DTOs;

public record UnitResponse(
        Long id,
        String friendlyName,
        String address,
        String description,
        Long userId
) {
}
