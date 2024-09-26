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

    Movement toMovement(MovementDto dto);

    MovementDto toMovementDto(Movement movement);

    List<MovementDto> toMovementDtos(List<Movement> movements);

    @Mapping(target = "id", ignore = true)
    void updateMovement(@MappingTarget Movement movement, MovementDto movementDto);
}
