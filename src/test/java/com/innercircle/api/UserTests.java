package com.innercircle.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innercircle.api.model.request.LoginUserRequest;
import com.innercircle.api.model.request.RegisterUserRequest;
import com.innercircle.api.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserTests {
    @Autowired
	private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    UserRepository userRepository;

    @Test
    public void registerUser_success() throws Exception{
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("some@gmail");
        registerUserRequest.setName("Tobby");
        registerUserRequest.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders
        .post("/api/register")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(registerUserRequest)))
        .andExpect(status().isOk());
    }

    @Test
    public void registerUser_error() throws Exception{
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("some@gmail");

        mockMvc.perform(MockMvcRequestBuilders
        .post("/api/register")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(registerUserRequest)))
        .andExpect(status().isBadRequest());
    }

    @Test
    public void login_success() throws Exception{
        LoginUserRequest userRequest = new LoginUserRequest();
        userRequest.setEmail("test@gmail.com");
        userRequest.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders
        .post("/api/login")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(userRequest)))
        .andExpect(status().isOk());
    }

    @Test
    public void login_notfound() throws Exception{
        LoginUserRequest userRequest = new LoginUserRequest();
        userRequest.setEmail("some@gmail");
        userRequest.setPassword("wrongPassword");

        mockMvc.perform(MockMvcRequestBuilders
        .post("/api/login")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(userRequest)))
        .andExpect(status().isNotFound());
    }

}
