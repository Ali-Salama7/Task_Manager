package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Status;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("getTask/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id){
        try{
            Optional<Task> task = taskService.getTaskById(id);
            return task.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("status/{status}")
    public ResponseEntity<List<Task>> getTaskByStatus(@PathVariable Status status){
         try{
             List<Task> tasks = taskService.getTaskByStatus(status);
             return ResponseEntity.status(HttpStatus.OK).body(tasks);
         } catch (Exception e) {
             e.printStackTrace();
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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

    @PutMapping("update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Task task){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, task));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(taskService.deleteTask(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed Delete.");
        }
    }


}
