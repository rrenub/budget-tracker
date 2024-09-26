package dev.rdelgado.budgettracker.api.budgetTrackerAPI.controllers;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.CategoryDto;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.Category;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoriesService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoriesService.getCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto categoryCreated = categoriesService.createCategory(categoryDto);
        return ResponseEntity.created(URI.create("/categories" + categoryCreated.getId())).body(categoryCreated);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> editCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoriesService.editCategory(id, categoryDto));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoriesService.deleteCategory(id));
    }
}
