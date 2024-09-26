package dev.rdelgado.budgettracker.api.budgetTrackerAPI.mappers;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.CategoryDto;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CategoriesMapper {

    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);

    List<CategoryDto> toCategoriesDto(List<Category> categories);
}
