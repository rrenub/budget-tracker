package dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.enums.MovementType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CategoryDto {
    private Long id;
    private String name;
    private String type;
}
