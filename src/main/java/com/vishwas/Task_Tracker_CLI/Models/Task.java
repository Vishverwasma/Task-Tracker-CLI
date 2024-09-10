package com.vishwas.Task_Tracker_CLI.Models;

import java.time.LocalDateTime;

import com.vishwas.Task_Tracker_CLI.Models.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String description;
	@Enumerated(EnumType.STRING)
	Status status;
    @Enumerated(EnumType.STRING)
    Priority priority;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	public Task() {
		super();
        this.priority = Priority.LOW; 
	    this.status = Status.TODO;
	}
	public Task(Long id, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Status status, Priority priority) {
		super();
		this.id = id;
		this.description = description;
	    this.status = status;
        this.priority = priority;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = Status.TODO;
        priority = Priority.LOW;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
