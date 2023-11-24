package com.billet.gestiontareas.controller;


import com.billet.gestiontareas.entities.Task;
import com.billet.gestiontareas.entities.User;
import com.billet.gestiontareas.exception.ResourceNotFoundException;
import com.billet.gestiontareas.service.TaskService;
import com.billet.gestiontareas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping()
    public ResponseEntity<Void> createNewUser(@RequestBody User user) {
        this.userService.createUser(user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        var user = this.userService.updateUser(id, updatedUser);

        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        this.userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<Set<Task>> getAllUserTasks(@PathVariable UUID id) {

        Set<Task> tasks = userService.getTasksByUser(id);

        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/{id}/tasks/{taskId}")
    public ResponseEntity<Void> addTaskToUser(@PathVariable UUID id, @PathVariable UUID taskId) {

        if (Objects.isNull(id)) throw new ResourceNotFoundException("User not found!!");

        if (Objects.isNull(taskId)) throw new ResourceNotFoundException("Task not found!!");

        userService.addTasksToUser(id, taskId);
        return ResponseEntity.noContent().build();

    }
}
