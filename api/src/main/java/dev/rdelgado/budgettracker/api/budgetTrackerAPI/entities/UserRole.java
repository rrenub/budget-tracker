package dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.enums.UserRoleType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRoleType roleType;
}
