package com.evaluacionformativa.listatareas.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Task {

    private int id;

    @NotBlank(message = "La descripcion no puede estar vacía")
    private String descripcion;

    @NotNull(message = "Debe indicar si está completada")
    private Boolean completado;
}
