package com.company.employee_management.controller;

import com.company.employee_management.model.Employee;
import com.company.employee_management.model.Task;
import com.company.employee_management.service.EmployeeService;
import com.company.employee_management.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeTaskController {

    private final TaskService taskService;
    private final EmployeeService employeeService;

    public EmployeeTaskController(TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    // TEMP: select employee by ID (you can replace this with login later)
    @GetMapping("/select")
    public String selectEmployeeForm(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employee/select-employee";
    }

    @GetMapping("/{employeeId}/tasks")
    public String viewTasks(@PathVariable Long employeeId, Model model) {
        List<Task> tasks = taskService.getTasksByEmployeeId(employeeId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("employeeId", employeeId);
        return "employee/task-list";
    }

    @PostMapping("/{employeeId}/tasks/{taskId}/update")
    public String updateTaskStatus(@PathVariable Long employeeId,
                                   @PathVariable Long taskId,
                                   @RequestParam("status") Task.Status status) {
        taskService.updateTaskStatus(taskId, status);
        return "redirect:/employee/" + employeeId + "/tasks";
    }
}
