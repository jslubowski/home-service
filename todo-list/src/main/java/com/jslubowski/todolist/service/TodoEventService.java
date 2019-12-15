package com.jslubowski.todolist.service;

import com.jslubowski.todolist.model.TodoEvent;
import com.jslubowski.todolist.repository.TodoEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoEventService {

    @Autowired
    TodoEventRepository todoEventRepository;

    public List<TodoEvent> getAllEvents(){
        List<TodoEvent> events = new ArrayList<>();
        todoEventRepository.findAll()
                .forEach(events::add);
        return events;
    }

    public TodoEvent getTodoEvent(Integer id) {
        return todoEventRepository.findById(id).get();
    }

    public void addEvent(TodoEvent event) {
        todoEventRepository.save(event);
    }


    public void deleteEvent(Integer id) {
        TodoEvent event = todoEventRepository.findById(id).get();
        todoEventRepository.delete(event);
    }

    public List<TodoEvent> searchForEvents(String text) {
        return todoEventRepository.searchForEvents(text);
    }

}
