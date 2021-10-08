package com.innercircle.api.model;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "user_resources")
public class UserResource {

    @Id
    @Column(name = "user_resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "status_id")
    private int statusId;

    @Column(name = "resource_id")
    private int resourceId;

    @Column(name = "date_started")
    private LocalDateTime startedOn;

    @Column(name = "date_finished")
    private LocalDateTime finishedOn;

    @Column(name = "is_favorite")
    private boolean favorite;

    @Column(name = "current_chapter")
    private int currentChapter;
    
}
