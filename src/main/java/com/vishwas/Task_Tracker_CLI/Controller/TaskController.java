package com.vishwas.Task_Tracker_CLI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vishwas.Task_Tracker_CLI.Models.Status;
import com.vishwas.Task_Tracker_CLI.Models.Task;
import com.vishwas.Task_Tracker_CLI.Service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public Task addTask(@RequestParam String description) {
        return taskService.addTask(description);
    }

    @GetMapping("/get")
    public List<Task> listTasks(@RequestParam(required = false) String status) {
        return taskService.listTasks(status);
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable Long id, @RequestParam String description) {
        return taskService.updateTask(id, description);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/update/{id}/mark-in-progress")
    public Task markInProgress(@PathVariable Long id) {
        return taskService.markTask(id, Status.PROGRESS);
    }

    @PutMapping("/update/{id}/mark-done")
    public Task markDone(@PathVariable Long id) {
        return taskService.markTask(id, Status.DONE);
    }
    @PutMapping("/update/{id}/mark-not-done")
    public Task markNotDone(@PathVariable Long id) {
        return taskService.markTaskAsNotDone(id);
    }
    @GetMapping("/get/done")
    public List<Task> listCompletedTasks() {
        return taskService.listCompletedTasks();
    }
    @GetMapping("/get/in-progress")
    public List<Task> listInProgressTasks() {
        return taskService.listInProgressTasks();
    }
    @GetMapping("/get/todo")
    public List<Task> listTodoTasks() {
        return taskService.listTodoTasks();
    }
    @GetMapping("/get/not-done")
    public List<Task> listNotDoneTasks() {
        return taskService.listNotDoneTasks();
    }
}