package com.company.employee_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String phone;
    private String address;

    private String idProofFileName; // Stores uploaded ID proof file name

    @Enumerated(EnumType.STRING)
    private Role role = Role.EMPLOYEE;

    public enum Role {
        ADMIN,
        EMPLOYEE
    }
}
