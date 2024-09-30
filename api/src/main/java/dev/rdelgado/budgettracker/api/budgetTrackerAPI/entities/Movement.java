package dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movement")
@Data
@ToString
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String concept;

    @Column
    private Float amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
