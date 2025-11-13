package com.expense_reporting_system.expense_reporting_system.service.impl;

import com.expense_reporting_system.expense_reporting_system.dto.ExpenseRequest;
import com.expense_reporting_system.expense_reporting_system.entity.Expense;
import com.expense_reporting_system.expense_reporting_system.entity.User;
import com.expense_reporting_system.expense_reporting_system.repository.ExpenseRepository;
import com.expense_reporting_system.expense_reporting_system.repository.UserRepository;
import com.expense_reporting_system.expense_reporting_system.service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import @Transactional
import java.time.OffsetDateTime;
import java.util.UUID;

@Service // Declares this class as a Spring Service Bean
public class ExpenseServiceImpl implements ExpenseService {

    // Use "Dependency Injection" to get the repositories
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    // Spring will automatically "inject" these beans via the constructor
    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional // A key skill!
    // Tells Spring that all database operations in this method
    // must either all succeed, or all fail (rollback).
    public Expense createExpense(ExpenseRequest expenseRequest, UUID submitterId) {
        
        // 1. Business Logic: Find the submitter by their ID
        User submitter = userRepository.findById(submitterId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + submitterId));
        
        // (We will replace RuntimeException with a custom exception later)

        // 2. Business Logic: Convert the DTO to an Entity
        Expense expense = new Expense();
        expense.setTitle(expenseRequest.getTitle());
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());

        // 3. Business Logic: Set business rules
        expense.setStatus("PENDING"); // Initial status for all new expenses
        expense.setSubmitter(submitter); // Link the expense to the user
        
        OffsetDateTime now = OffsetDateTime.now();
        expense.setCreatedAt(now);
        expense.setUpdatedAt(now);

        // 4. Save to the database (via the repository)
        return expenseRepository.save(expense);
    }
}