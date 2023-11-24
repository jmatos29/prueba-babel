package com.billet.gestiontareas.service;

import com.billet.gestiontareas.entities.Task;
import com.billet.gestiontareas.exception.ResourceNotFoundException;
import com.billet.gestiontareas.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {


    private final TaskRepository taskRepository;

    /*
     * This method is to fetch all the task saved in the database.
     * */
    @Override
    @Transactional
    public List<Task> getAllTasks() {
        log.info("Getting all the tasks");
        return this.taskRepository.findAll();
    }

    @Override
    @Transactional
    public Task getTaskById(UUID id) {
        if(Objects.isNull(id)) {
            throw new IllegalArgumentException("id cannot be null");
        }
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task no found"));
    }

    /*
     * This method is to create a new task.
     * */
    @Override
    public void createTask(Task task) {
        log.info("Saving a new task");

        this.taskRepository.save(task);
    }

    /*
    * This method is to update a task, you need provide a task to be updated successfully.
    * */
    @Override
    @Transactional
    public Task updateTask(UUID id, Task task) {

        if (Objects.isNull(id)) throw new IllegalArgumentException("The id provided is null");

        if (Objects.isNull(task)) throw new IllegalArgumentException("The task provided is null");

        if(!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("task not found");
        }
        task.setId(id);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(UUID id) {
        if(!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("task not found");
        }
        this.taskRepository.deleteById(id);
    }


}
