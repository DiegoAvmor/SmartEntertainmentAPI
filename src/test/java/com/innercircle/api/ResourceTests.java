package com.innercircle.api;

import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innercircle.api.model.request.LoginUserRequest;
import com.innercircle.api.model.request.UserResourceLibraryRequest;
import com.innercircle.api.model.request.UserResourceRequest;
import com.innercircle.api.repository.ResourceRepository;
import com.innercircle.api.repository.UserResourceRepository;
import com.innercircle.api.service.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ResourceTests {

    @Autowired
	private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    UserResourceRepository userResourceRepository;

    @Autowired
    ResourceService resourceService;

    private String obtainAccessToken(String username, String password) throws Exception {
 
        LoginUserRequest userRequest = new LoginUserRequest();
        userRequest.setEmail(username);
        userRequest.setPassword(password);
    
        ResultActions result 
          = mockMvc.perform(MockMvcRequestBuilders
          .post("/api/login")
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
          .content(this.mapper.writeValueAsString(userRequest)))
          .andExpect(status().isOk());
    
        String resultString = result.andReturn().getResponse().getContentAsString();
    
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("token").toString();
    }

    @Test
    public void searchResource_existingResource() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/resource/search?query=Halo&resourceType=1"))
        .andExpect(status().isOk());
    }

    @Test
    public void getReourceTypes_existingResourceTypes() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/resource/type"))
        .andExpect(status().isOk());
    }

    @Test
    public void getResourceStatus_existingResourceStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/resource/status"))
        .andExpect(status().isOk());
    }

    @Test
    public void addResourceToLibrary_success() throws Exception{
        UserResourceRequest request = new UserResourceRequest();
        request.setResource(2);
        request.setFavorite(true);
        request.setCurrent_chapter(10);
        request.setFinished_on(LocalDateTime.now());
        request.setStarted_on(LocalDateTime.now());
        request.setStatus(1);

        String accessToken = obtainAccessToken("test@gmail.com", "password");


        mockMvc.perform(MockMvcRequestBuilders
        .post("/api/library/me/resource")
        .header("Authorization", "Bearer " + accessToken)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(request)))
        .andExpect(status().isOk());
    }

    @Test
    public void addResourceToLibrary_invalidAccess() throws Exception{
        UserResourceRequest request = new UserResourceRequest();
        request.setResource(1);
        request.setFavorite(true);
        request.setCurrent_chapter(10);
        request.setFinished_on(LocalDateTime.now());
        request.setStarted_on(LocalDateTime.now());
        request.setStatus(1);

        mockMvc.perform(MockMvcRequestBuilders
        .post("/api/library/me/resource")
        .header("Authorization", "Bearer " + "020d9as9d0as0d0as0dasd")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(request)))
        .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteResourceFromUserLibrary_invalidAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/library/me/resource/1")
        .header("Authorization", "Bearer " + "020d9as9d0as0d0as0dasd"))
        .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteResourceFromUserLibrary_success() throws Exception{
        String accessToken = obtainAccessToken("test@gmail.com", "password");
        mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/library/me/resource/12")
        .header("Authorization", "Bearer " + accessToken))
        .andExpect(status().isAccepted());
    }

    @Test
    public void deleteResourceFromUserLibrary_forbiddenAcess() throws Exception{
        String accessToken = obtainAccessToken("test1@gmail.com", "password");
        mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/library/me/resource/12")
        .header("Authorization", "Bearer " + accessToken))
        .andExpect(status().isForbidden());
    }

    @Test
    public void updateResourceFromUserLibrary_invalidAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        .put("/api/library/me/resource/1")
        .header("Authorization", "Bearer " + "020d9as9d0as0d0as0dasd"))
        .andExpect(status().isUnauthorized());
    }

    @Test
    public void updateResourceFromUserLibrary_success() throws Exception{
        UserResourceLibraryRequest request = new UserResourceLibraryRequest();
        request.setFavorite(false);
        request.setCurrent_chapter(5);
        request.setFinished_on(LocalDateTime.now());
        request.setStarted_on(LocalDateTime.now());
        request.setStatus(3);

        String accessToken = obtainAccessToken("test@gmail.com", "password");

        mockMvc.perform(MockMvcRequestBuilders
        .put("/api/library/me/resource/12")
        .header("Authorization", "Bearer " + accessToken)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(request)))
        .andExpect(status().isOk());
    }

    @Test
    public void updateResourceFromUserLibrary_forbiddenAccess() throws Exception{
        UserResourceLibraryRequest request = new UserResourceLibraryRequest();
        request.setFavorite(false);
        request.setCurrent_chapter(5);
        request.setFinished_on(LocalDateTime.now());
        request.setStarted_on(LocalDateTime.now());
        request.setStatus(3);

        String accessToken = obtainAccessToken("test1@gmail.com", "password");

        mockMvc.perform(MockMvcRequestBuilders
        .put("/api/library/me/resource/12")
        .header("Authorization", "Bearer " + accessToken)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(request)))
        .andExpect(status().isForbidden());
    }

}
