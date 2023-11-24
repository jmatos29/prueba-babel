package com.billet.gestiontareas.service;

import com.billet.gestiontareas.entities.Task;
import com.billet.gestiontareas.entities.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(UUID id);

    void createTask(Task task);

    Task updateTask(UUID id ,Task task);

    void deleteTask(UUID id);
}
