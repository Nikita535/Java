package com.example.Controller;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task/ff")
@RequiredArgsConstructor
public class FireAndForgetController {
    private final RSocketRequester rSocketRequester;

    @DeleteMapping
    public Publisher<Void> deleteTask(@RequestParam(name = "id") Long id){
        return rSocketRequester
                .route("deleteTask")
                .data(id)
                .send();
    }

}
