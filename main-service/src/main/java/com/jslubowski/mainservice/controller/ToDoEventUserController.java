package com.jslubowski.mainservice.controller;

import com.jslubowski.mainservice.model.TodoEvent;
import com.jslubowski.mainservice.service.TodoEventService;
import com.jslubowski.mainservice.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class ToDoEventUserController {

    private final TodoEventService todoEventService;

    @GetMapping("/events")
    public List<TodoEvent> getAllEventsForUser(Principal principal){
        String username = Utilities.currentUserName(principal);
        return todoEventService.getAllEventsForUser(username);
    }

    @GetMapping("/events/{id}")
    public TodoEvent getEventById(@PathVariable Long id, Principal principal){
        String username = Utilities.currentUserName(principal);
        return todoEventService.getTodoEventForUser(id, username);
    }

    @PostMapping(value = "/events")
    public void addEvent(@RequestBody TodoEvent event, Principal principal){
        // TODO
        todoEventService.addEventForUser(event, principal);
    }

    @DeleteMapping(value = "/events/{id}")
    public void deleteTopic(@PathVariable String id, Principal principal){
        String username = Utilities.currentUserName(principal);
        todoEventService.deleteEventForUser(Long.parseLong(id), username);
    }

    @GetMapping("/events/search/{name}")
    public List<TodoEvent> searchForEvents(@PathVariable String name, Principal principal){
        String username = Utilities.currentUserName(principal);
        return todoEventService.searchForEventForUser(name, username);
    }

}
