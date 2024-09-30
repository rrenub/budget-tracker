package dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MovementDto {

    private Long id;
    private String concept;
    private Float amount;
    private Long categoryId; // Relation to category (ManyToOne)
    private String categoryName;
}
