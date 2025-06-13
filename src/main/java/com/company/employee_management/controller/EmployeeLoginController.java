package com.company.employee_management.controller;

import com.company.employee_management.model.Employee;
import com.company.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employee")
public class EmployeeLoginController {

    @Autowired
    private EmployeeService employeeService;

    // GET - Show login page
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Invalid Username or Password");
        }
        return "employee/login"; // maps to templates/employee/login.html
    }

    // POST - Process login form
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              RedirectAttributes redirectAttributes) {

        Employee employee = employeeService.findByUsername(username);

        if (employee == null || !employee.getPassword().equals(password)) {
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/employee/login";
        }

        // TODO: Add session logic here
        return "redirect:/employee/dashboard"; // or another secured employee page
    }
}
