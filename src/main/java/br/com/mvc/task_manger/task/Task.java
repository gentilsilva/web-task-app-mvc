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

    public String getDescription() {
        return this.description;
    }

    public Status getStatus() {
        return this.status;
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

    public LocalDateTime getFinished() {
        return this.finished;
    }

    private LocalDateTime dateTimeFormatter(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = formatter.format(localDateTime);
        return LocalDateTime.parse(formattedDate, formatter);
    }

}
