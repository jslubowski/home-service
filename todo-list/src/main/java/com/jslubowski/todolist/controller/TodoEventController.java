package com.jslubowski.todolist.controller;

import com.jslubowski.todolist.model.TodoEvent;
import com.jslubowski.todolist.service.TodoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TodoEventController {

    @Autowired
    private TodoEventService todoEventService;

    @RequestMapping("/events")
    public List<TodoEvent> getAllTodoEvents(){
        return todoEventService.getAllEvents();
    }

    @RequestMapping("/events/{id}")
    public TodoEvent getEventById(@PathVariable("id") String id){
        return todoEventService.getTodoEvent(Integer.parseInt(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/events")
    public void addEvent(@RequestBody TodoEvent event){
        todoEventService.addEvent(event);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/events/{id}")
    public void deleteTopic(@PathVariable("id") String id){
        todoEventService.deleteEvent(Integer.parseInt(id));
    }

    @RequestMapping("/events/search/{text}")
    public List<TodoEvent> searchForEvents(@PathVariable("text") String text){
        return todoEventService.searchForEvents(text);
    }

}
