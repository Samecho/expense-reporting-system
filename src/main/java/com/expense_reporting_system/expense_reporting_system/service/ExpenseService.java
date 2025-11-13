package com.expense_reporting_system.expense_reporting_system.service;

import com.expense_reporting_system.expense_reporting_system.dto.ExpenseRequest;
import com.expense_reporting_system.expense_reporting_system.entity.Expense;

import java.util.UUID;

// This is the interface (the "contract")
public interface ExpenseService {

    /**
     * Creates a new expense report for a given user.
     * @param expenseRequest The DTO containing expense details (title, amount)
     * @param submitterId The ID of the user submitting the report
     * @return The newly created and saved Expense entity
     */
    Expense createExpense(ExpenseRequest expenseRequest, UUID submitterId);

}