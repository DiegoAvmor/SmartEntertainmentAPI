package com.innercircle.api.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String name;

    @Column
    private String email;

    @Column
    @JsonIgnore
    private String password;

}
