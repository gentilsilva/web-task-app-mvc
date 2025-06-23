package br.com.mvc.task_manger.task;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TaskService {

    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void create(DataCreateTask data) {
        taskRepository.save(new Task(data));
    }

    public List<DataReadTask> readAll() {
        return taskRepository.findAll().stream().map(DataReadTask::new).toList();
    }
}
