package dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MovementDto {

    private Long id;

    @NotBlank
    private String concept;

    @NotNull
    private Float amount;

    @NotNull
    private Long categoryId; // Relation to category (ManyToOne)

    private String categoryName;
}
