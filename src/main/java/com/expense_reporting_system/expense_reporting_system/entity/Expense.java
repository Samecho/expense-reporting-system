package com.expense_reporting_system.expense_reporting_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal; // Use BigDecimal for currency
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for SQL's BIGSERIAL
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount; // Maps to DECIMAL(10, 2)

    @Column(name = "status", nullable = false)
    private String status; // e.g., 'PENDING', 'APPROVED', 'REJECTED'

    // --- Relationships ---

    @ManyToOne(fetch = FetchType.LAZY) // Many Expenses to One User (Submitter)
    @JoinColumn(name = "submitter_id", referencedColumnName = "id", nullable = false) // Maps the foreign key column
    private User submitter;

    @ManyToOne(fetch = FetchType.LAZY) // Many Expenses to One User (Approver)
    @JoinColumn(name = "approver_id", referencedColumnName = "id") // This column can be null
    private User approver;

    // --- Timestamps ---

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}