package com.example.Controller;

import com.example.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/task/rr")
@RequiredArgsConstructor
public class RequestResponseController {
    private final RSocketRequester rSocketRequester;

    @GetMapping("/{id}")
    public Mono<TaskDto> getTask(@PathVariable Long id) {
        return rSocketRequester
                .route("getTask")
                .data(id)
                .retrieveMono(TaskDto.class);
    }

    @PostMapping
    public Mono<TaskDto> addTask(@RequestBody TaskDto task) {
        return rSocketRequester
                .route("addTask")
                .data(task)
                .retrieveMono(TaskDto.class);
    }
}
