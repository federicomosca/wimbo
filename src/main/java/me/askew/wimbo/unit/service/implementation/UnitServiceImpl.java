package me.askew.wimbo.unit.service.implementation;

import lombok.RequiredArgsConstructor;
import me.askew.wimbo.unit.domain.DTOs.UnitRequest;
import me.askew.wimbo.unit.domain.DTOs.UnitResponse;
import me.askew.wimbo.unit.domain.model.Unit;
import me.askew.wimbo.unit.repository.UnitRepository;
import me.askew.wimbo.unit.service.UnitService;
import me.askew.wimbo.user.domain.model.User;
import me.askew.wimbo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final UserRepository userRepository;

    @Override
    public UnitResponse createUnit(UnitRequest req) {
        Unit unit = new Unit();
        unit.setFriendlyName(req.friendlyName());
        unit.setAddress(req.address());
        unit.setDescription(req.description());
        User user = userRepository.findById(req.userId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
        unit.setUser(user);
        unit = unitRepository.save(unit);

        return new UnitResponse(
                unit.getId(),
                unit.getFriendlyName(),
                unit.getAddress(),
                unit.getDescription(),
                unit.getUser().getId()
        );
    }

    @Override
    public UnitResponse getUnit(Long id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid unit id"));

        return new UnitResponse(
                unit.getId(),
                unit.getFriendlyName(),
                unit.getAddress(),
                unit.getDescription(),
                unit.getUser().getId()
        );
    }

    @Override
    public UnitResponse updateUnit(Long id, UnitRequest unitRequest) {
        Unit unitToUpdate = unitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid unit id"));
        unitToUpdate.setFriendlyName(unitRequest.friendlyName());
        unitToUpdate.setAddress(unitRequest.address());
        unitToUpdate.setDescription(unitRequest.description());
        unitToUpdate = unitRepository.save(unitToUpdate);

        return new UnitResponse(
                unitToUpdate.getId(),
                unitToUpdate.getFriendlyName(),
                unitToUpdate.getAddress(),
                unitToUpdate.getDescription(),
                unitToUpdate.getUser().getId()
        );
    }

    @Override
    public void deleteUnit(Long id) {
        Unit unitToDelete = unitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid unit id"));

        unitRepository.delete(unitToDelete);
    }

    @Override
    public List<UnitResponse> getUnits() {
        return unitRepository.findAll().stream()
                .map(unit -> new UnitResponse(
                        unit.getId(),
                        unit.getFriendlyName(),
                        unit.getAddress(),
                        unit.getDescription(),
                        unit.getUser().getId()
                ))
                .toList();
    }

    @Override
    public UnitResponse getUnitsByUserId(Long userId) {

        return null;
    }
}
