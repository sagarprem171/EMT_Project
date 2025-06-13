package com.company.employee_management.service;

import com.company.employee_management.model.Task;
import com.company.employee_management.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Save a new task
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Delete task
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    // Get tasks assigned to a specific employee
    public List<Task> getTasksByEmployeeId(Long employeeId) {
        return taskRepository.findByAssignedToId(employeeId);
    }

    // Update status of a task
    public void updateTaskStatus(Long taskId, Task.Status status) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        taskRepository.save(task);
    }
}
