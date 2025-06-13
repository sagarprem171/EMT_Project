package com.company.employee_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee assignedTo;

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED,
        FAILED
    }
}
