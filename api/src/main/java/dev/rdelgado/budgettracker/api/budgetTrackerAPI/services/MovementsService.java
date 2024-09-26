package dev.rdelgado.budgettracker.api.budgetTrackerAPI.services;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.MovementDto;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.Movement;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.exceptions.AppException;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.mappers.MovementMapper;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.repositories.MovementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovementsService {

    private final MovementsRepository movementsRepository;
    private final MovementMapper movementMapper;

    public List<MovementDto> getAllMovements() {
        return movementMapper.toMovementDtos(movementsRepository.findAll());
    }

    public MovementDto createMovement(MovementDto movementDto) {
        Movement movement = movementMapper.toMovement(movementDto);
        Movement createdMovement = movementsRepository.save(movement);

        return movementMapper.toMovementDto(createdMovement);
    }

    public MovementDto deleteMovement(Long id) {
        Movement deletedMovement = movementsRepository
                .findById(id)
                .orElseThrow(() -> new AppException("Movement not found", HttpStatus.NOT_FOUND));

        movementsRepository.deleteById(id);

        return movementMapper.toMovementDto(deletedMovement);
    }

    public MovementDto updateMovement(Long id, MovementDto movementDto) {
        Movement movementToUpdate = movementsRepository
                .findById(id)
                .orElseThrow(() -> new AppException("Movement not found", HttpStatus.NOT_FOUND));

        movementMapper.updateMovement(movementToUpdate, movementDto);

        Movement movementUpdated = movementsRepository.save(movementToUpdate);

        return movementMapper.toMovementDto(movementUpdated);
    }
}
