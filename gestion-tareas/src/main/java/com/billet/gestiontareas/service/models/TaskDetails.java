package com.billet.gestiontareas.service.models;

import com.billet.gestiontareas.entities.TaskStatus;
import com.billet.gestiontareas.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public record TaskDetails(String id,
                          String taskName,
                          String taskDescription,
                          LocalDateTime createdDate,
                          LocalDateTime expiredDate,
                          TaskStatus status) {
}
