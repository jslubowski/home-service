package com.jslubowski.mainservice.repository;


import com.jslubowski.mainservice.model.TodoEvent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TodoEventRepository extends CrudRepository<TodoEvent, Long> {

    @Query("select event from TodoEvent event where event.name like %:text%")
    List<TodoEvent> searchForEvents(@Param("text") String text);

    @Query("select event from TodoEvent event where event.name like %:text% and owner.userName like :userName")
    List<TodoEvent> searchForEventForUser(@Param("text") String event, @Param("userName") String userName);

    @Query("select event from TodoEvent event where owner.userName like :userName")
    List<TodoEvent> getAllEventsForUser(@Param("userName") String userName);

    @Query("select event from TodoEvent event where owner.userName like :userName and event.eventId=:eventId")
    TodoEvent getTodoEventForUser(@Param("userName") String userName, @Param("eventId") Long id);

    @Modifying
    @Transactional
    @Query("delete from TodoEvent event where event.eventId=:eventId and owner.userName like :userName")
    void deleteEventForUser(@Param("eventId") Long id, @Param("userName") String userName);

}
