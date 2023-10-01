package com.example.Controller;


import com.example.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/task/ch")
@RequiredArgsConstructor
public class ChannelController {

    private final RSocketRequester rSocketRequester;


    @PostMapping
    public Flux<TaskDto> addTasksMultiple(@RequestBody List<TaskDto> tasks){
        Flux<TaskDto> tasks2 = Flux.fromIterable(tasks);
        return rSocketRequester
                .route("taskChannel")
                .data(tasks2)
                .retrieveFlux(TaskDto.class);
    }
}
