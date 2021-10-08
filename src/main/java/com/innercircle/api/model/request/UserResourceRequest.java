package com.innercircle.api.model.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResourceRequest {
    
    @NotNull
    private int resource;
    @NotNull
    private int status;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime started_on;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime finished_on;
    @JsonProperty
    private boolean favorite = false;
    private int current_chapter = 0;


}
