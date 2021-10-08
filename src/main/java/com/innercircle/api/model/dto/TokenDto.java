package com.innercircle.api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {

    private String token;
    private String type = "Bearer";
    
}
