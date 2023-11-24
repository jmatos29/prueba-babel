package com.billet.gestiontareas.service.models;

import com.billet.gestiontareas.entities.Task;

import java.util.List;

public record UserInfo(String id,
                       String firstName,
                       String lastName,
                       String email) {
}
