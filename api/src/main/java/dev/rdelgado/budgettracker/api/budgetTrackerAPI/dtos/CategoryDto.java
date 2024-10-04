package dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.annotations.ValueOfEnum;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CategoryDto {

    private Long id;

    @NotBlank(message = "Invalid category name")
    private String name;

    @ValueOfEnum(enumClass = MovementType.class, message = "Invalid type of movement")
    private String type;
}
