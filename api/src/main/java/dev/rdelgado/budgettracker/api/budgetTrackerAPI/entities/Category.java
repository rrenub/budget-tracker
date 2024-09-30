package dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.enums.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "category")
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private MovementType type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movement> movements;
}
