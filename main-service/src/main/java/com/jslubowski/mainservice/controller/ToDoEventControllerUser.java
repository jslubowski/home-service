package com.jslubowski.mainservice.controller;

import com.jslubowski.todolist.model.TodoEvent;
import com.jslubowski.todolist.service.TodoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoEventControllerUser {

    @Autowired
    private TodoEventService todoEventService;

    @RequestMapping("/{userId}/events")
    public List<TodoEvent> getAllTodoEvents(){
        return todoEventService.getAllEvents();
    }

    @RequestMapping("/{userId}/events/{id}")
    public TodoEvent getEventById(@PathVariable("id") String id){
        return todoEventService.getTodoEvent(Long.parseLong(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}/events")
    public void addEvent(@RequestBody TodoEvent event){
        todoEventService.addEvent(event);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}/events/{id}")
    public void deleteTopic(@PathVariable("id") String id){
        todoEventService.deleteEvent(Long.parseLong(id));
    }

    @RequestMapping("/{userId}/events/search/{text}")
    public List<TodoEvent> searchForEvents(@PathVariable("text") String text){
        return todoEventService.searchForEvents(text);
    }
}
