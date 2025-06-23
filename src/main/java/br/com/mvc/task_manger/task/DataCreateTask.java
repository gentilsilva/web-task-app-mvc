package br.com.mvc.task_manger.task;

public record DataCreateTask(
        String title,
        String description,
        Priority priority
) {
}
