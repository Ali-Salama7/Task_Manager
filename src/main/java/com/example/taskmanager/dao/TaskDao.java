package com.example.taskmanager.dao;

import com.example.taskmanager.entity.Status;
import com.example.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDao extends JpaRepository<Task, Integer> {
    List<Task> findByStatus(Status status);
}
