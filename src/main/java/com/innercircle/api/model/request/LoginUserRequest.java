package com.innercircle.api.model.request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

}
