package br.com.mvc.task_manger.task;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
class TaskService {

    private final TaskRepository taskRepository;
    private final TaskHistoryRepository taskHistoryRepository;

    TaskService(TaskRepository taskRepository, TaskHistoryRepository taskHistoryRepository) {
        this.taskRepository = taskRepository;
        this.taskHistoryRepository = taskHistoryRepository;
    }

    @Transactional
    public void create(DataCreateTask data) {
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
        boolean isUpdated = false;
        Task taskToUpdate = taskRepository.findById(data.id()).orElseThrow();

        System.out.println(taskToUpdate.getId() + taskToUpdate.getTitle());

        if(!data.title().isEmpty()) {
            taskToUpdate.setTitle(data.title());
        }
        if(!data.description().isEmpty()) {
            taskToUpdate.setDescription(data.description());
        }
        if(data.status() != null) {
            taskToUpdate.setStatus(data.status());
            isUpdated = true;
        }

        if(isUpdated) {
            TaskHistory taskHistory = new TaskHistory(taskToUpdate, taskToUpdate.getStatus(), LocalDateTime.now());
            taskHistoryRepository.save(taskHistory);
        }

        taskRepository.save(taskToUpdate);
    }
}
