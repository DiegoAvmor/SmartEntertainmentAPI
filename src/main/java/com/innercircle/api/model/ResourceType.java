package com.innercircle.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "resource_types")
public class ResourceType {

    @Id
    @Column(name = "resource_type_id")
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "resourceType")
    @JsonBackReference
    private List<Resource> resources;
}
