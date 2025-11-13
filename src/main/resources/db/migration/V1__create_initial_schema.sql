-- Create initial database schema

-- 1. Table for users (employees, managers, etc.)
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- e.g., 'ROLE_EMPLOYEE', 'ROLE_MANAGER'
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- 2. Table for the core expense reports
CREATE TABLE expenses (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    amount DECIMAL(10, 2) NOT NULL, -- e.g., 99999999.99
    status VARCHAR(50) NOT NULL, -- 'PENDING', 'APPROVED', 'REJECTED'
    
    submitter_id UUID NOT NULL, -- Foreign key to the users table
    approver_id UUID, -- Foreign key to the users table (nullable)

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    -- Define the foreign key constraints
    CONSTRAINT fk_submitter
        FOREIGN KEY(submitter_id) 
        REFERENCES users(id)
        ON DELETE SET NULL, -- If user is deleted, keep the expense report
    
    CONSTRAINT fk_approver
        FOREIGN KEY(approver_id) 
        REFERENCES users(id)
        ON DELETE SET NULL
);

-- Add indexes for faster lookups on foreign keys
CREATE INDEX idx_expenses_submitter_id ON expenses(submitter_id);
CREATE INDEX idx_expenses_status ON expenses(status);