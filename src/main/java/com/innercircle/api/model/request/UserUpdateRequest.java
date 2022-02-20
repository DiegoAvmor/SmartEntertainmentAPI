package com.innercircle.api.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    @NotEmpty
    private String name;

    @NotEmpty @Size(max = 10)
    private String password;
}
