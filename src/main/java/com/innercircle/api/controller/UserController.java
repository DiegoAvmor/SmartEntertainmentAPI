package com.innercircle.api.controller;

import javax.validation.Valid;
import com.innercircle.api.model.User;
import com.innercircle.api.model.dto.TokenDto;
import com.innercircle.api.model.request.LoginUserRequest;
import com.innercircle.api.model.request.RegisterUserRequest;
import com.innercircle.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginUserRequest request) {
        return ResponseEntity.ok().body(userService.loadUser(request));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterUserRequest request){
        return ResponseEntity.ok().body(userService.registerUser(request));
    }
    
}
