package com.jslubowski.mainservice.controller;


import com.jslubowski.mainservice.model.TodoEvent;
import com.jslubowski.mainservice.service.TodoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class ToDoEventControllerUser {


    @Autowired
    private TodoEventService todoEventService;

    /*
        All mappings created for the user that is in session
     */

    // ------------------------------------ Get All Events -------------------------------------------------------


    @RequestMapping("/events")
    public List<TodoEvent> getAllEventsForUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return todoEventService.getAllEventsForUser(username);
    }


    // ------------------------------------ Get one event -------------------------------------------------------


    @RequestMapping("/events/{id}")
    public TodoEvent getEventById(@PathVariable("id") Long id){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return todoEventService.getTodoEventForUser(id, username);
    }


    // ------------------------------------ Add one event -------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST, value = "/events")
    public void addEvent(@RequestBody TodoEvent event){
        // TODO How?
        todoEventService.addEvent(event);
    }


    // ------------------------------------ Delete one event -------------------------------------------------------

    @RequestMapping(method = RequestMethod.DELETE, value = "/events/{id}")
    public void deleteTopic(@PathVariable("id") String id){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        todoEventService.deleteEventForUser(Long.parseLong(id), username);
    }


    // ------------------------------------ Search through events for user -------------------------------------------------------

    @RequestMapping("/events/search/{name}")
    public List<TodoEvent> searchForEvents(@PathVariable("name") String name){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return todoEventService.searchForEventForUser(name, username);
    }
}
