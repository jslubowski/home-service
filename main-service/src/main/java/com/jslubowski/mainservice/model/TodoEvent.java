package com.jslubowski.mainservice.model;

import lombok.Data;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class TodoEvent implements Serializable {

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
    private String location;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn
    private User owner;

    @Transient
    private String uuid = UUID.randomUUID().toString();


    @Override
    public boolean equals(Object that) {
        return this == that || that instanceof TodoEvent
                && Objects.equals(uuid, ((TodoEvent) that).uuid);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uuid)
                .toHashCode();
    }
}
