package com.company.employee_management.service;

import com.company.employee_management.model.Employee;
import com.company.employee_management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    public Employee findByUsername(String username) {
        return repository.findByUsername(username);
    }

    // Updated to save uploaded ID proof
    public void registerNewEmployee(Employee employee, MultipartFile idProof) {
        try {
            if (idProof != null && !idProof.isEmpty()) {
                String uploadDir = "uploads/idproofs";
                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) uploadFolder.mkdirs();

                String filename = employee.getUsername() + "_" + idProof.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, filename);
                Files.write(filePath, idProof.getBytes());

                employee.setIdProofFileName(filename);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload ID proof", e);
        }

        repository.save(employee);
    }
}
