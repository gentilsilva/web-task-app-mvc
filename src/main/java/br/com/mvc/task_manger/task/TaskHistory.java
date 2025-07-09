package br.com.mvc.task_manger.task;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "status_history")
class TaskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Task task;
    private Status status;
    private LocalDateTime updated;

    protected TaskHistory() {}

    TaskHistory(Task task, Status status, LocalDateTime updated) {
        this.task = task;
        this.status = status;
        this.updated = dateTimeFormatter(updated);
    }

    public LocalDateTime getUpdate() {
        return this.updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = dateTimeFormatter(updated);
    }

    private LocalDateTime dateTimeFormatter(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = formatter.format(localDateTime);
        return LocalDateTime.parse(formattedDate, formatter);
    }

}
