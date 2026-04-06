package com.evaluacionformativa.listatareas.controller;

import com.evaluacionformativa.listatareas.model.Task;
import com.evaluacionformativa.listatareas.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las peticiones de las Tareas.
 * Aquí definimos las URLs (endpoints) que el mundo exterior puede visitar.
 * Es la puerta de entrada a la aplicación
 *
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

    /**
     * Endpoint para listar todas las tareas
     * Metodo HTTP: GET
     * URL: /api/v1/tareas
     */
    @GetMapping
    public ResponseEntity<List<Task>> obtenerTareas() {
        try {
            // Nota: Corregí "obtenerTodos()" por "obtenerTodas()" basándome en tu Servicio anterior
            List<Task> tareas = taskService.obtenerTodos();

            // Retorna la lista con un código 200 (OK)
            return new ResponseEntity<>(tareas, HttpStatus.OK);

        } catch (Exception e) {
            // Solución: Usamos ResponseEntity.status().build() para evitar el uso de "null"
            // Esto crea una respuesta vacía con el código 500 (INTERNAL_SERVER_ERROR)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint para agregar una nueva tarea.
     * Metodo HTTP: POST
     * URL: /api/v1/tareas
     */
    @PostMapping
    public ResponseEntity<Task> crearTarea(@Valid @RequestBody Task tarea) {
        //La anotación @Valid le dice a Spring que revise los @NotBlank y @NotNull de tu clase Task
        //@RequestBody indica que los datos de la tarea vienen en el "cuerpo" de la petición HTTP
        try{
            Task nuevaTarea = taskService.crearTarea(tarea);
            //Retorna la tarea creada con un código 201 (CREATED)
            return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint para eliminar una tarea por su ID.
     * Metodo HTTP: DELETE
     * URL: /api/v1/tareas/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTareas(@PathVariable("id") int id) {
        //@PathVariable extrae el {id} directamente de la URL
        try{
            boolean eliminado = taskService.eliminarTarea(id);
            if(eliminado) {
                //Si el servicio devolvió true, se eliminó con éxito
                return new ResponseEntity<>("Tarea eliminada correctamente", HttpStatus.OK);
            } else {
                //Si el servicio devolvió false, el ID no existía en nuestra lista
                return new ResponseEntity<>("No se encontró la tarea con el ID indicado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Error al intentar eliminar la tarea", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
