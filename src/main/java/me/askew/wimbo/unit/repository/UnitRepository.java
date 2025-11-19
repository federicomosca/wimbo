package me.askew.wimbo.unit.repository;

import me.askew.wimbo.unit.domain.DTOs.UnitResponse;
import me.askew.wimbo.unit.domain.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository <Unit, Long>{

    Optional<UnitResponse>findByUserId(Long user_id);
}
