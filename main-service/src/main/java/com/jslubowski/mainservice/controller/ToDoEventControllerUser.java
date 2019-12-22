package com.jslubowski.mainservice.controller;


import com.jslubowski.mainservice.model.TodoEvent;
import com.jslubowski.mainservice.service.TodoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoEventControllerUser {


    @Autowired
    private TodoEventService todoEventService;

    /*
        All mappings created for particular user
     */

    // ------------------------------------ Get All Events -------------------------------------------------------

    @RequestMapping("/{userId}/events")
    public List<TodoEvent> getAllTodoEvents(){
        return todoEventService.getAllEvents();
    }

    // ------------------------------------ Get one event -------------------------------------------------------

    @RequestMapping("/{userId}/events/{id}")
    public TodoEvent getEventById(@PathVariable("id") String id){
        return todoEventService.getTodoEvent(Long.parseLong(id));
    }

    // ------------------------------------ Add one event -------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}/events")
    public void addEvent(@RequestBody TodoEvent event){
        todoEventService.addEvent(event);
    }

    // ------------------------------------ Delete one event -------------------------------------------------------

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}/events/{id}")
    public void deleteTopic(@PathVariable("id") String id){
        todoEventService.deleteEvent(Long.parseLong(id));
    }

    // ------------------------------------ Saerch through events -------------------------------------------------------

    @RequestMapping("/{userId}/events/search/{name}")
    public List<TodoEvent> searchForEvents(@PathVariable("name") String name, @PathVariable("userId") String user){
        return todoEventService.searchForEventsForUser(name, user);
    }
}
