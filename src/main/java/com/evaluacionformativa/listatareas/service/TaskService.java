package com.evaluacionformativa.listatareas.service;

import com.evaluacionformativa.listatareas.model.Task;
import com.evaluacionformativa.listatareas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para manejar la lógica de negocio de las tareas
 * Conecta el Contralador (que crearemos después) con el Repositorio
 */
@Service // Esta notación es calve para que Spring Boot reconozca esta clase como un Servicio
public class TaskService {

    //Declaramos nuestro repositorio para poder usarlo
    private TaskRepository taskRepository;

    //Usamos el constructor para "inyectar" el repositorio.
    //Es una buena práctica de Spring Boot (Inyección de Dependencias).
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     *
     * Obtiene la lista completa de tareas.
     */
    public List<Task> obtenerTodos(){
        //Le pedimos al repositorio que nos devuelva todo lo que tiene guardado
        return taskRepository.findAll();
    }

    /**
     *
     * Guarda una nueva tarea
     */
    public Task crearTarea(Task tarea){
        // Aquí es donde iría la lógica de negocio extra si la tuviéramos
        // (Por ejemplo, revisar que el nombre no contenga groserías antes de guardar)

        // Como no tenemos reglas extra por ahora, simplemente le decimos al repositorio que la guarde
        return taskRepository.save(tarea);
    }

    /**
     * Elimina una tarea por su ID.
     */
    public boolean eliminarTarea(int id){
        //El repositorio intentará borrarla y nos devolverá true (si pudo) o false (si no existía)
        return taskRepository.deleteById(id);
    }

}
