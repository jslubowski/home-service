package com.jslubowski.mainservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
public class User {

    // -------------------------------- fields --------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    @JsonIgnore
    private String userName;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean active;

    @JsonIgnore
    private String roles;

    public User() {
    }
}
