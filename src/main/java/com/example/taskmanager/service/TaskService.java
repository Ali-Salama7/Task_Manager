package com.example.taskmanager.service;

import com.example.taskmanager.dao.TaskDao;
import com.example.taskmanager.dto.TaskRequestDTO;
import com.example.taskmanager.entity.Status;
import com.example.taskmanager.entity.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;


    public void addTask(@Valid TaskRequestDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setDueDate(taskDTO.getDueDate());
        taskDao.save(task);
    }

    public List<Task> getAllTask() {
        try{
            return taskDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Optional<Task> getTaskById(Integer id) {
        return taskDao.findById(id);
    }

    public Task updateTask(Integer id, Task task) {
        Task taskOptional = taskDao.findById(id).orElseThrow(
                () -> new RuntimeException("Question not found")
        );
        taskOptional.setTitle(task.getTitle());
        taskOptional.setDescription(task.getDescription());
        taskOptional.setStatus(task.getStatus());
        taskOptional.setPriority(task.getPriority());
        taskOptional.setCreatedAt(task.getCreatedAt());
        return taskDao.save(taskOptional);
    }

    public List<Task> getTaskByStatus(Status status) {
        return taskDao.findByStatus(status);
    }

    public String deleteTask(Integer id) {
        if (taskDao.existsById(id)){
            taskDao.deleteById(id);
            return "Task deleted successfully.";
        } else {
            return "Task not found with ID: " + id;
        }
    }
}
