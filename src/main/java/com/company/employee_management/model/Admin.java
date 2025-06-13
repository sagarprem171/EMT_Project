package com.company.employee_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"AdminDetails\"")  // Quoted for exact casing in PostgreSQL
public class Admin {

    @Id
    @Column(name = "\"AdminId\"")
    private String adminId;

    @Column(name = "\"AdminOrgCode\"")
    private String adminOrgCode;

    @Column(name = "\"AdminName\"")
    private String adminName;

    @Column(name = "\"AdminEmailId\"")
    private String adminEmailId;

    @Column(name = "\"AdminPassword\"")
    private String adminPassword;

    @Column(name = "\"AdminMobileNumber\"")
    private String adminMobileNumber;

    // Getters and Setters

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminOrgCode() {
        return adminOrgCode;
    }

    public void setAdminOrgCode(String adminOrgCode) {
        this.adminOrgCode = adminOrgCode;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmailId() {
        return adminEmailId;
    }

    public void setAdminEmailId(String adminEmailId) {
        this.adminEmailId = adminEmailId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminMobileNumber() {
        return adminMobileNumber;
    }

    public void setAdminMobileNumber(String adminMobileNumber) {
        this.adminMobileNumber = adminMobileNumber;
    }
}
