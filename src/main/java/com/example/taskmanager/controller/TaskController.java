package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("getAll")
    public ResponseEntity<List<Task>> getAllTask(){
        return taskService.getAllTask();
    }

    @PostMapping("add")
    public ResponseEntity<String> addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

}
