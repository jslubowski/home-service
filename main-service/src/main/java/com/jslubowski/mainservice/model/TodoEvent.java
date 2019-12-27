package com.jslubowski.mainservice.model;

import lombok.Data;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class TodoEvent implements Serializable {

    // -------------------------------- fields --------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String location;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn
    private User owner;


    // // -------------------------------- equals and hashCode --------------------------------

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
        if (!location.equals(event.location)) return false;
        if (!description.equals(event.description)) return false;
        return owner.equals(event.owner);
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
                .append(location)
                .append(description)
                .append(owner.getUserName())
                .toHashCode();
    }
}
