package com.jslubowski.mainservice.repository;


import com.jslubowski.mainservice.model.TodoEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoEventRepository extends CrudRepository<TodoEvent, Long> {

    @Query("select event from TodoEvent event where event.name like %:text%")
    List<TodoEvent> searchForEvents(@Param("text") String text);

    @Query("select event from TodoEvent event where event.name like %:text%")
    List<TodoEvent> searchForEventsForUser(@Param("text") String event);

}
