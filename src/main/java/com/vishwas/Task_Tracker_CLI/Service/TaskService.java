package com.vishwas.Task_Tracker_CLI.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishwas.Task_Tracker_CLI.Models.Status;
import com.vishwas.Task_Tracker_CLI.Models.Task;
import com.vishwas.Task_Tracker_CLI.Repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private final TaskRepository taskRepository;
	public TaskService(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}
	public Task addTask(String description) {
		Task task = new Task();
		task.setDescription(description);
		return taskRepository.save(task);
	}
    public List<Task> listTasks(String status) {
        if (status == null) {
            return taskRepository.findAll();
        }
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus().name().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
	public Task updateTask(Long id, String description) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setDescription(description);
        return taskRepository.save(task);
    }
	public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
	public Task markTask(Long id, Status status) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }
    public List<Task> listCompletedTasks() {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus() == Status.DONE)
                .collect(Collectors.toList());
    }
    public List<Task> listInProgressTasks() {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus() == Status.PROGRESS)
                .collect(Collectors.toList());
    }
    public List<Task> listTodoTasks() {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus() == Status.TODO)
                .collect(Collectors.toList());
    }
    public List<Task> listNotDoneTasks() {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus() == Status.TODO || task.getStatus() == Status.PROGRESS)
                .collect(Collectors.toList());
    }
    public Task markTaskAsNotDone(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setStatus(Status.TODO);
        return taskRepository.save(task);
    }
} 
