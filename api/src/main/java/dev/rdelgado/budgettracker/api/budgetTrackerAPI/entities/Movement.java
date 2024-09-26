package dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movement")
@ToString
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String concept;

    @Column
    private Float amount;

}
