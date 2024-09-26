package dev.rdelgado.budgettracker.api.budgetTrackerAPI.services;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.CategoryDto;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.Category;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.mappers.CategoriesMapper;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.repositories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final CategoriesMapper categoriesMapper;

    public List<CategoryDto> getCategories() {
        List<Category> categories = categoriesRepository.findAll();
        return categoriesMapper.toCategoriesDto(categories);
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoriesMapper.toCategory(categoryDto);
        Category categoryCreated = categoriesRepository.save(category);

        return categoriesMapper.toCategoryDto(categoryCreated);
    }
}
