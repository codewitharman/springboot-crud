-- This file runs automatically when the app starts.
-- It pre-fills the database with 5 sample employees.

INSERT INTO employees (first_name, last_name, email, department, salary)
VALUES ('Alice', 'Johnson', 'alice@example.com', 'Engineering', 85000.00);

INSERT INTO employees (first_name, last_name, email, department, salary)
VALUES ('Bob', 'Smith', 'bob@example.com', 'Marketing', 72000.00);

INSERT INTO employees (first_name, last_name, email, department, salary)
VALUES ('Carol', 'Williams', 'carol@example.com', 'HR', 68000.00);

INSERT INTO employees (first_name, last_name, email, department, salary)
VALUES ('David', 'Brown', 'david@example.com', 'Engineering', 90000.00);

INSERT INTO employees (first_name, last_name, email, department, salary)
VALUES ('Eva', 'Davis', 'eva@example.com', 'Finance', 78000.00);