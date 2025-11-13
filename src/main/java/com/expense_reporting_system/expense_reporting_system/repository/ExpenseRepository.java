package com.expense_reporting_system.expense_reporting_system.repository;

import com.expense_reporting_system.expense_reporting_system.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // JpaRepository<Expense, Long>
    // - 'Expense' is the entity this repository manages.
    // - 'Long' is the type of the primary key (Expense.id).

    // Spring Data JPA magic:
    
    // "SELECT * FROM expenses WHERE submitter_id = ?"
    List<Expense> findBySubmitterId(UUID submitterId);

    // "SELECT * FROM expenses WHERE approver_id = ?"
    List<Expense> findByApproverId(UUID approverId);

    // "SELECT * FROM expenses WHERE status = ?"
    List<Expense> findByStatus(String status);
}