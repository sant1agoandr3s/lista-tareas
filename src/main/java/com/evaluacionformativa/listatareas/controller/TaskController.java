package com.evaluacionformativa.listatareas.controller;

import com.evaluacionformativa.listatareas.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para manejar las peticiones de las Tareas.
 * Aquí definimos las URLs (endpoints) que el mundo exterior puede visitar.
 */
@RestController // Indica que esta clase responde a peticiones web y devuelve datos en formato JSON
@RequestMapping("/api/v1/tareas") //Define la URL base exigida por la evaluación
public class TaskController {

    private final TaskService taskService;

    // Inyectamos el servicio para poder pasarle las órdenes
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
}
