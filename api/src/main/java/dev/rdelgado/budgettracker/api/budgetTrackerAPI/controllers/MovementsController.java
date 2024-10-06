package dev.rdelgado.budgettracker.api.budgetTrackerAPI.controllers;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.MovementDto;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.Movement;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.services.MovementsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovementsController {

    private final MovementsService movementsService;

    @GetMapping("/movements")
    public ResponseEntity<List<MovementDto>> allMovements() {
        return ResponseEntity.ok(movementsService.getAllMovements());
    }

    @PostMapping("/movements")
    public ResponseEntity<MovementDto> createMovement(@Valid @RequestBody MovementDto movementDto) {

         MovementDto createdMovement = movementsService.createMovement(movementDto);

         return ResponseEntity.created(URI.create("/movements/" + createdMovement.getId())).body(createdMovement);
    }

    @DeleteMapping("/movements/{id}")
    public ResponseEntity<MovementDto> deleteMovement(@PathVariable Long id) {
        return ResponseEntity.ok().body(movementsService.deleteMovement(id));
    }

    @PutMapping("/movements/{id}")
    public ResponseEntity<MovementDto> updateMovement(@PathVariable Long id, @Valid @RequestBody MovementDto movementDto) {
        return ResponseEntity.ok(movementsService.updateMovement(id, movementDto));
    }
}
