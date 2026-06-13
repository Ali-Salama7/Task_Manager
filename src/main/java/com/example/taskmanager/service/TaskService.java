package com.example.taskmanager.service;

import com.example.taskmanager.dao.TaskDao;
import com.example.taskmanager.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;


    public Task addTask(Task task) {
        return taskDao.save(task);
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
}
