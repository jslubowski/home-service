package com.jslubowski.mainservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String name;

    @Column(name="begin_time")
    private String beginTime;

    @Column(name="end_time")
    private String endTime;

    @Column(name="begin_date")
    private String beginDate;

    @Column(name="end_date")
    private String endDate;

    private String location;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User owner;

    @Transient
    @JsonIgnore
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
