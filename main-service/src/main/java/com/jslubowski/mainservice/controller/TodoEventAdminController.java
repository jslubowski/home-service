package com.jslubowski.mainservice.controller;

import com.jslubowski.mainservice.exceptions.EventNotFoundException;
import com.jslubowski.mainservice.model.TodoEvent;
import com.jslubowski.mainservice.service.TodoEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class TodoEventAdminController {

    private final TodoEventService todoEventService;

    @GetMapping("/events")
    public List<TodoEvent> getAllTodoEvents(){
        return todoEventService.getAllEvents();
    }

    @GetMapping("/events/{id}")
    public TodoEvent getEventById(@PathVariable String id){
        try {
            return todoEventService.getTodoEvent(Long.parseLong(id));
        }catch(EventNotFoundException e){
            e.getMessage();
        }
        return null;
    }

    @PostMapping(value = "events")
    public void addEvent(@RequestBody TodoEvent event){
        todoEventService.addEvent(event);
    }

    @DeleteMapping(value = "/events/{id}")
    public void deleteTopic(@PathVariable String id){
        todoEventService.deleteEvent(Long.parseLong(id));
    }

    @GetMapping("/events/search/{text}")
    public List<TodoEvent> searchForEvents(@PathVariable String text){
        return todoEventService.searchForEvents(text);
    }

}
