package br.com.mvc.task_manger.task;

import java.time.LocalDateTime;

public record DataReadTask(
        Integer id,
        String title,
        String description,
        Status status,
        Priority priority
) {
    DataReadTask(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority());
    }
}
