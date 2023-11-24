package com.billet.gestiontareas.entities;

public enum TaskStatus {

    PENDIENTE("Pendinte"),
    EN_PROCESO("En proceso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada"),
    INCOMPLETA("Incompleta");

    private final String status;

    TaskStatus(String status) {this.status = status;}

    public String getStatus() {return status;}


}
