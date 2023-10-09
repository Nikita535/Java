package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    @MessageMapping("getTask")
    public Mono<Task> getTask(Long id) {
        return Mono.justOrEmpty(taskRepository.findById(id));
    }

    @MessageMapping("addTask")
    public Mono<Task> addTask(Task task) {
        System.out.println("Поступил запрос на сервер добавить сущность: "+ task.toString());
        return Mono.justOrEmpty(taskRepository.save(task));
    }

    @MessageMapping("getTasks")
    public Flux<Task> getTasks() {
        return Flux.fromIterable(taskRepository.findAll());
    }

    @MessageMapping("deleteTask")
    public Mono<Void> deleteTask(Long id){
        Task task = taskRepository.findById(id).get();
        System.out.println("Поступил запрос на сервер удалить сущность: "+ task.toString());
        taskRepository.delete(task);
        return Mono.empty();
    }

    @MessageMapping("taskChannel")
    public Flux<Task> TaskChannel(Flux<Task> Tasks){
        return Tasks.flatMap(Task -> Mono.fromCallable(() -> taskRepository.save(Task)))
                .collectList()
                .flatMapMany(savedTasks -> Flux.fromIterable(savedTasks));

    }
}
