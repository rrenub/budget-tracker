package dev.rdelgado.budgettracker.api.budgetTrackerAPI.repositories;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.UserRole;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.enums.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByRoleType(UserRoleType role);
}
