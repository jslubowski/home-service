package com.jslubowski.todolist.controller;

import com.jslubowski.todolist.model.TodoEvent;
import com.jslubowski.todolist.service.TodoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TodoEventControllerAdmin {

    @Autowired
    private TodoEventService todoEventService;

    /*
    * Admin mappings - crud operation without checking for ownership
     */

    @RequestMapping("/admin/events")
    public List<TodoEvent> getAllTodoEvents(){
        return todoEventService.getAllEvents();
    }

    @RequestMapping("/admin/events/{id}")
    public TodoEvent getEventById(@PathVariable("id") String id){
        return todoEventService.getTodoEvent(Long.parseLong(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/events")
    public void addEvent(@RequestBody TodoEvent event){
        todoEventService.addEvent(event);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/events/{id}")
    public void deleteTopic(@PathVariable("id") String id){
        todoEventService.deleteEvent(Long.parseLong(id));
    }

    @RequestMapping("/admin/events/search/{text}")
    public List<TodoEvent> searchForEvents(@PathVariable("text") String text){
        return todoEventService.searchForEvents(text);
    }

}
