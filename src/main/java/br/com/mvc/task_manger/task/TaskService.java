package br.com.mvc.task_manger.task;

import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
class TaskService {

    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void create(DataCreateTask data) {
        System.out.println("Passei por aqui");
        taskRepository.save(new Task(data));
    }

    public List<DataReadTask> readAll() {
        return taskRepository.findAll().stream().map(DataReadTask::new).toList();
    }

    public DataReadTask readById(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow();
        System.out.println(task);
        return new DataReadTask(task);
    }

    public void update(DataReadTask data) {
        Task updatedTask = taskRepository.findById(data.id()).orElseThrow();

        System.out.println(updatedTask.getId() + updatedTask.getTitle());

        if(!data.title().isEmpty()) {
            updatedTask.setTitle(data.title());
        }
        if(!data.description().isEmpty()) {
            updatedTask.setDescription(data.description());
        }

        // Necessário rever o status, form não tem a opção
        updatedTask.setStatus(data.status());
        updatedTask.setUpdated(LocalDateTime.now());
        taskRepository.save(updatedTask);
    }
}
