package dev.rdelgado.budgettracker.api.budgetTrackerAPI.repositories;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
