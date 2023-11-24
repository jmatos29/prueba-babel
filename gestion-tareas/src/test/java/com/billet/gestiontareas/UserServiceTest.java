package com.billet.gestiontareas;

import com.billet.gestiontareas.entities.Task;
import com.billet.gestiontareas.entities.TaskStatus;
import com.billet.gestiontareas.entities.User;
import com.billet.gestiontareas.repository.UserRepository;
import com.billet.gestiontareas.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;


    @InjectMocks private UserServiceImpl userService;

    @Test
    public void testGetTasksByUser() {
        // Crear un usuario de prueba
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        // Crear un conjunto de tareas de prueba
        Task task1 = new Task(UUID.randomUUID(), "Tarea 1", "Descripcion 1", LocalDateTime.MAX, LocalDateTime.MAX, TaskStatus.EN_PROCESO);
        Task task2 = new Task(UUID.randomUUID(), "Tarea 2", "Descripcion 2", LocalDateTime.MAX, LocalDateTime.MAX, TaskStatus.EN_PROCESO);
        user.setTasks(Set.of(task1, task2));

        // Configurar el comportamiento esperado del repositorio mock
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Llamar al metodo que se está probando
        Set<Task> result = userService.getTasksByUser(userId);

        // Verificar que el resultado sea el esperado
        assertEquals(user.getTasks(), result);
    }

    @Test
    public void testGetTasksByUserWithNoUser() {
        // Crear un usuario de prueba
        UUID userId = UUID.randomUUID();

        // Configurar el comportamiento esperado del repositorio mock
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Llamar al método que se está probando
        Set<Task> result = userService.getTasksByUser(userId);

        // Verificar que el resultado sea un conjunto vacío
        assertEquals(Collections.emptySet(), result);
    }
}
