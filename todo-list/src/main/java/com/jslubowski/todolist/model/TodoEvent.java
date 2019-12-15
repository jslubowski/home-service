package com.jslubowski.todolist.model;

import lombok.Data;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class TodoEvent implements Serializable {

    // == fields ==
    @Id
    @GeneratedValue
    private Long eventId;

    @Column(name="name")
    private String name;

    @Column(name="begin_time")
    private String beginTime;

    @Column(name="end_time")
    private String endTime;

    @Column(name="begin_date")
    private String beginDate;

    @Column(name="end_date")
    private String endDate;

    @Column(name="place")
    private String place;

    @Column(name="description")
    private String description;

    // == equals and hashCode ==

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoEvent)) return false;

        TodoEvent event = (TodoEvent) o;

        if (!name.equals(event.name)) return false;
        if (!beginTime.equals(event.beginTime)) return false;
        if (!endTime.equals(event.endTime)) return false;
        if (!beginDate.equals(event.beginDate)) return false;
        if (!endDate.equals(event.endDate)) return false;
        if (!place.equals(event.place)) return false;
        return description.equals(event.description);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(eventId)
                .append(name)
                .append(beginTime)
                .append(endTime)
                .append(beginDate)
                .append(endDate)
                .append(place)
                .append(description)
                .toHashCode();
    }
}
