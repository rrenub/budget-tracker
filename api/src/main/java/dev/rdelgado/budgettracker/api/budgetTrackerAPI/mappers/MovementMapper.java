package dev.rdelgado.budgettracker.api.budgetTrackerAPI.mappers;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.MovementDto;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MovementMapper {

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "categoryName", target = "category.name")
    Movement toMovement(MovementDto dto);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    MovementDto toMovementDto(Movement movement);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "categoryName", target = "category.name")
    List<MovementDto> toMovementDtos(List<Movement> movements);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "categoryName", target = "category.name")
    void updateMovement(@MappingTarget Movement movement, MovementDto movementDto);
}
