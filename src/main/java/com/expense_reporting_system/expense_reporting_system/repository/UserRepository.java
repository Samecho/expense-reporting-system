package com.expense_reporting_system.expense_reporting_system.repository;

import com.expense_reporting_system.expense_reporting_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository // Declares this as a Spring Bean (for database operations)
public interface UserRepository extends JpaRepository<User, UUID> {
    // JpaRepository<User, UUID>
    // - 'User' is the entity this repository manages.
    // - 'UUID' is the type of the primary key (User.id).
    
    // Spring Data JPA will automatically create a query for this method:
    // "SELECT * FROM users WHERE username = ?"
    Optional<User> findByUsername(String username);

    // "SELECT * FROM users WHERE email = ?"
    Optional<User> findByEmail(String email);

    // We will add more custom queries here later.
}