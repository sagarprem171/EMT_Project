package com.company.employee_management.repository;

import com.company.employee_management.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    // ✅ Updated to match Admin.java field names exactly
    Optional<Admin> findByAdminIdAndAdminOrgCodeAndAdminPassword(String adminId, String adminOrgCode, String adminPassword);
}
