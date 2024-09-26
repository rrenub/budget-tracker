package dev.rdelgado.budgettracker.api.budgetTrackerAPI.repositories;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
}
