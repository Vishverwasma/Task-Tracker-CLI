package com.vishwas.Task_Tracker_CLI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishwas.Task_Tracker_CLI.Models.Task;

public interface TaskRepository extends JpaRepository<Task , Long> {

}
