package com.company.employee_management.repository;

import com.company.employee_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Custom method to find employee by username
    Employee findByUsername(String username);
}
