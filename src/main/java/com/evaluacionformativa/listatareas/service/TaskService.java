package com.evaluacionformativa.listatareas.service;

import com.evaluacionformativa.listatareas.model.Task;
import com.evaluacionformativa.listatareas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> obtenerTodos(){
        return taskRepository.findAll();
    }

}
