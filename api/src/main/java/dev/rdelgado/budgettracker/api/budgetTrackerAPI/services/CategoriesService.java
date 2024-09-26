package dev.rdelgado.budgettracker.api.budgetTrackerAPI.services;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.CategoryDto;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.Category;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.exceptions.AppException;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.mappers.CategoriesMapper;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.repositories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    public CategoryDto editCategory(Long id, CategoryDto categoryDto) {
        Category categoryToEdit = categoriesRepository
                .findById(id)
                .orElseThrow(() -> new AppException("Category to edit not found", HttpStatus.NOT_FOUND));

        categoriesMapper.updateCategory(categoryToEdit, categoryDto);

        Category categoryEdited = categoriesRepository.save(categoryToEdit);
        return categoriesMapper.toCategoryDto(categoryEdited);
    }

    public CategoryDto deleteCategory(Long id) {
        Category categoryToDelete = categoriesRepository
                .findById(id)
                .orElseThrow(() -> new AppException("Category to delete not found", HttpStatus.NOT_FOUND));
        categoriesRepository.delete(categoryToDelete);

        return categoriesMapper.toCategoryDto(categoryToDelete);
    }
}
