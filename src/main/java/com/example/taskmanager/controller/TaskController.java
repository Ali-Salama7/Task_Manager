package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskRequestDTO;
import com.example.taskmanager.entity.Status;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Task>> getAllTask(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTask(keyword, page, size, sortBy));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
    public ResponseEntity<String> addTask(@Valid @RequestBody TaskRequestDTO taskDTO){
        try{
            taskService.addTask(taskDTO);
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
