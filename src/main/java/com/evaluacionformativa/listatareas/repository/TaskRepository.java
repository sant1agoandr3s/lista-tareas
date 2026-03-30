/**
* Repositorio para la entidad Task.
* Almacena los datos en memoria utilizando un ArrayList.
*/
package com.evaluacionformativa.listatareas.repository;
import com.evaluacionformativa.listatareas.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    /**
     lista estática en memoria
     */
    private final List<Task> tareas =  new ArrayList<>();

    /**
     * Un contador manual para simular los IDs autoincrementables de una base de datos
     */
    private int idContador = 1L;

    /**
     * Método para obtener la lista completa de tareas
     */
    public List<Task> findAll() {
        return tareas;
    }
    /**
     * Metodo para guardar una nueva tarea en la lista
     */
    public Task save(Task task) {
        /**
         * Le asignamos un ID único y luego aumentamos el contador
         */
        task.setId(idContador++);

        /**
         * Si no se especificó si está completa, por defecto la ponemos en false
         */
        if(task.getCompletado() == null){
            task.setCompletado(false);
        }
        /**
         * Agregamos la tarea a nuestro ArrayList
         */
        tareas.add(task);
        return task;
    }

    /**
     * Método para eliminar una tarea utilizando su ID
     */
    public boolean deleteById(int id) {
        return tareas.removeIf(task -> task.getId() == id);
    }
}
