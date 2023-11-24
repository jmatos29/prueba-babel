package com.billet.gestiontareas.service;

import com.billet.gestiontareas.entities.Task;
import com.billet.gestiontareas.entities.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(UUID id);

    void createUser(User user);

    User updateUser(UUID id , User user);

    void deleteUser(UUID id);

    void addTasksToUser(UUID id, UUID tasksId);

    Set<Task> getTasksByUser(UUID id);

}
