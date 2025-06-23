package br.com.mvc.task_manger.task;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tasks")
class TaskController {

    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ModelAttribute("priorities")
    List<Priority> priorities() {
        return Arrays.stream(Priority.values()).toList();
    }

    @GetMapping
    String getTaskListPage(Model model) {
        List<String> columns = Arrays.stream(DataReadTask.class.getRecordComponents())
                .map(RecordComponent::getName)
                .toList();
        List<DataReadTask> tasks = taskService.readAll();

        model.addAttribute("columns", columns);
        model.addAttribute("tasks", tasks);

        return TaskPages.LIST_PAGE;
    }

    @GetMapping("/form")
    String getRegisterPage(Integer id, Model model) {
        if(id != null) {
            model.addAttribute("data", taskService.readById(id));
            return TaskPages.UPDATE_PAGE;
        } else {
            model.addAttribute("data", new DataCreateTask("", "", null));
        }
        return TaskPages.REGISTER_PAGE;
    }

    @PostMapping("/create-task")
    String createNewTask(@ModelAttribute("data") DataCreateTask data, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "ERRO";
        } else {
            taskService.create(data);
        }
        return TaskPages.REDIRECT_LIST_PAGE;
    }

    @PostMapping("/update-task")
    String updateTask(@ModelAttribute("data") DataReadTask data, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "ERRO";
        } else {
            taskService.update(data);
        }
        return TaskPages.REDIRECT_LIST_PAGE;
    }

}
