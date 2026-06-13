package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("getAll")
    public ResponseEntity<List<Task>> getAllTask(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTask());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }

    @PostMapping("add")
    public ResponseEntity<String> addTask(@RequestBody Task task){
        try{
            taskService.addTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create task");
        }
    }

}
