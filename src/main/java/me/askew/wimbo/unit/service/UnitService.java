package me.askew.wimbo.unit.service;

import me.askew.wimbo.unit.domain.DTOs.UnitRequest;
import me.askew.wimbo.unit.domain.DTOs.UnitResponse;

import java.util.List;

public interface UnitService {

    UnitResponse createUnit(UnitRequest unitRequest);
    UnitResponse getUnit(Long id);
    UnitResponse updateUnit(Long id, UnitRequest unitRequest);
    void deleteUnit(Long id);
    List<UnitResponse> getUnits();
    UnitResponse getUnitsByUserId(Long userId);
}
