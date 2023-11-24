package com.billet.gestiontareas.service;

import com.billet.gestiontareas.entities.Task;
import com.billet.gestiontareas.entities.User;
import com.billet.gestiontareas.exception.ResourceNotFoundException;
import com.billet.gestiontareas.repository.TaskRepository;
import com.billet.gestiontareas.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Log4j2
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUserById(UUID id) {
        if(Objects.isNull(id)) {
            throw new IllegalArgumentException("id cannot be null");
        }
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public void createUser(User user) {

        if (Objects.isNull(user)) throw new ResourceNotFoundException("The user provided is null");

        this.userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(UUID id, User user) {

        if (Objects.isNull(id)) throw new ResourceNotFoundException("The id provided is null");

        if (Objects.isNull(user)) throw new ResourceNotFoundException("The user provided is null");

        if(!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("resource not found");
        }

        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        User user = this.getUserById(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void addTasksToUser(UUID id, UUID tasksId) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Task task = taskRepository.findById(tasksId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        user.getTasks().add(task);

        userRepository.save(user);
    }

    @Override
    public Set<Task> getTasksByUser(UUID id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getTasks();
        }
        return Collections.emptySet();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new UserPrincipal(user);
    }
}
