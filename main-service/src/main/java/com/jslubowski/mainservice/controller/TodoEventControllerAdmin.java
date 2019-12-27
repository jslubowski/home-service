package com.jslubowski.mainservice.controller;


import com.jslubowski.mainservice.model.TodoEvent;
import com.jslubowski.mainservice.service.TodoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class TodoEventControllerAdmin {

    @Autowired
    private TodoEventService todoEventService;

    /*
    * Admin mappings - crud operation without checking for ownership
     */

    // ------------------------------------ Get All Events -------------------------------------------------------

    @RequestMapping("/events")
    public List<TodoEvent> getAllTodoEvents(){
        return todoEventService.getAllEvents();
    }

    // ------------------------------------ Get one Events -------------------------------------------------------

    @RequestMapping("/events/{id}")
    public TodoEvent getEventById(@PathVariable("id") String id){
        return todoEventService.getTodoEvent(Long.parseLong(id));
    }

    // ------------------------------------ Add one event -------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST, value = "events")
    public void addEvent(@RequestBody TodoEvent event){
        todoEventService.addEvent(event);
    }

    // ------------------------------------ Delete one event -------------------------------------------------------

    @RequestMapping(method = RequestMethod.DELETE, value = "/events/{id}")
    public void deleteTopic(@PathVariable("id") String id){
        todoEventService.deleteEvent(Long.parseLong(id));
    }

    // ------------------------------------ Search for event based on name -------------------------------------------------------

    @RequestMapping("/events/search/{text}")
    public List<TodoEvent> searchForEvents(@PathVariable("text") String text){
        return todoEventService.searchForEvents(text);
    }

}
