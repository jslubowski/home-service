package com.jslubowski.mainservice.service;

import com.jslubowski.mainservice.exceptions.EventNotFoundException;
import com.jslubowski.mainservice.model.TodoEvent;
import com.jslubowski.mainservice.model.User;
import com.jslubowski.mainservice.repository.TodoEventRepository;
import com.jslubowski.mainservice.repository.UserRepository;
import com.jslubowski.mainservice.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoEventService {

    private final TodoEventRepository todoEventRepository;
    private final UserRepository userRepository;

    public List<TodoEvent> getAllEvents(){
        List<TodoEvent> events = new ArrayList<>();
        todoEventRepository.findAll()
                .forEach(events::add);
        return events;
    }

    public TodoEvent getTodoEvent(Long id) throws EventNotFoundException {
        Optional<TodoEvent> event = todoEventRepository.findById(id);
        event.orElseThrow(()-> new EventNotFoundException("Event of id: " + id + " doesn't exist."));
        return todoEventRepository.findById(id).get();
    }

    public TodoEvent getTodoEventForUser(Long id, String username){
        return todoEventRepository.getTodoEventForUser(username, id);
    }

    public void addEvent(TodoEvent event) {
        todoEventRepository.save(event);
    }

    public void addEventForUser(TodoEvent event, Principal principal){
        String username = Utilities.currentUserName(principal);
        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        event.setOwner(user.get());
        todoEventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        TodoEvent event = todoEventRepository.findById(id).get();
        todoEventRepository.delete(event);
    }

    public List<TodoEvent> searchForEvents(String text) {
        return todoEventRepository.searchForEvents(text);
    }

    public List<TodoEvent> searchForEventForUser(String name, String userName) {
        return todoEventRepository.searchForEventForUser(name, userName);
    }

    public List<TodoEvent> getAllEventsForUser(String username){
        return todoEventRepository.getAllEventsForUser(username);
    }

    public void deleteEventForUser(Long id, String username){
        todoEventRepository.deleteEventForUser(id, username);
    }


}
