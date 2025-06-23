package br.com.mvc.task_manger.task;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tasks")
class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime finished;

    protected Task() {}

    Task(DataCreateTask data) {
        this.title = data.title();
        this.description = data.description();
        this.status = Status.OPEN;
        this.priority = data.priority();
        this.created = dateTimeFormatter(LocalDateTime.now());
        this.updated = null;
        this.finished = null;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public LocalDateTime getCreate() {
        return this.created;
    }

    public LocalDateTime getUpdate() {
        return this.updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = dateTimeFormatter(updated);
    }

    public LocalDateTime getFinished() {
        return this.finished;
    }

    public void setFinished(LocalDateTime finished) {
        this.finished = dateTimeFormatter(finished);
    }

    private LocalDateTime dateTimeFormatter(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = formatter.format(localDateTime);
        return LocalDateTime.parse(formattedDate, formatter);
    }

}
