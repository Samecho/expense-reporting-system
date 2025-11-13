package com.expense_reporting_system.expense_reporting_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data // Lombok: Generates getters, setters, toString(), etc.
@NoArgsConstructor // Lombok: JPA requires a no-args constructor
@AllArgsConstructor // Lombok: Convenience constructor
@Entity // JPA: Declares this class as a database entity
@Table(name = "users") // JPA: Maps this class to the "users" table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO strategy for UUID
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash; // Maps to "password_hash" column

    @Column(name = "role", nullable = false)
    private String role; // e.g., 'ROLE_EMPLOYEE', 'ROLE_MANAGER'

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}