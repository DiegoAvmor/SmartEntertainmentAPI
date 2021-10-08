package com.innercircle.api.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "resource")
public class Resource {
    @Id
    @Column(name = "resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "resourceType")
    @JsonManagedReference
    private ResourceType resourceType;

    @Column
    private String image_url;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "year_publish")
    private int year;

    @Column(name = "num_chapter")
    private int chapters;


}
