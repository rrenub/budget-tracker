package dev.rdelgado.budgettracker.api.budgetTrackerAPI.repositories;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementsRepository extends JpaRepository<Movement, Long> {

}
