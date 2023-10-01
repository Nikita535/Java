package com.example.Controller;

import com.example.TaskDto;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task/rs")
@RequiredArgsConstructor
public class RequestStreamController {

    private final RSocketRequester rSocketRequester;


    @GetMapping
    public Publisher<TaskDto> getTasks() {
        System.out.println("Поступил запрос на получение всех сущностей");
        return rSocketRequester
                .route("getTasks")
                .data(new TaskDto())
                .retrieveFlux(TaskDto.class);
    }
}
