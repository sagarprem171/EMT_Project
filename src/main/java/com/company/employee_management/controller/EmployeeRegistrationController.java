package com.company.employee_management.controller;

import com.company.employee_management.model.Employee;
import com.company.employee_management.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/employee")
public class EmployeeRegistrationController {

    private final EmployeeService employeeService;

    public EmployeeRegistrationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/employee-register";
    }

    @PostMapping("/register")
    public String registerEmployee(
            @ModelAttribute Employee employee,
            @RequestParam("idProof") MultipartFile idProof,
            Model model) {

        // Save employee with uploaded file (TODO: add service logic for this)
        employeeService.registerNewEmployee(employee, idProof);

        // Redirect to login or confirmation page
        return "redirect:/employee/login";
    }
}
