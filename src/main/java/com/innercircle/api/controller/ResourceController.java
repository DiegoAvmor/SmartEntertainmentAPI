package com.innercircle.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.innercircle.api.model.Resource;
import com.innercircle.api.model.ResourceType;
import com.innercircle.api.model.Status;
import com.innercircle.api.model.UserResource;
import com.innercircle.api.model.request.UserResourceLibraryRequest;
import com.innercircle.api.model.request.UserResourceRequest;
import com.innercircle.api.service.ResourceService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/resource/search")
    public Page<Resource> displayAllResources(@RequestParam String query, Pageable pageable) {
        return resourceService.searchResource(query, pageable);
    }

    @GetMapping("/resource/type")
    public ResponseEntity<List<ResourceType>> getResourceTypes(){
        return ResponseEntity.ok().body(resourceService.getResourceTypes());
    }

    @GetMapping("/resource/status")
    public ResponseEntity<List<Status>> getResourceStatus(){
        return ResponseEntity.ok().body(resourceService.getResourceStatus());
    }

    @PostMapping("/library/me/resource")
    public ResponseEntity<UserResource> register(@RequestBody @Valid UserResourceRequest request){
        return ResponseEntity.ok().body(resourceService.addResourceToUserLibrary(request));
    }

    @GetMapping("/library/me/resource/{id}")
    public ResponseEntity<UserResource> getUserResourceById(@PathVariable int id){
        return ResponseEntity.ok().body(resourceService.findInUserLibraryByResourceId(id));
    }

    @GetMapping("/library/me/resource")
    public Page<UserResource> getCurrentUserLibrary(Pageable pageable){
        return resourceService.getCurrentUserLibrary(pageable);
    }

    @DeleteMapping("/library/me/resource/{id}")
    public ResponseEntity<UserResource> deleteResourceFromLibrary(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resourceService.deleteResourceFromUserLibrary(id));
    }

    @PutMapping("/library/me/resource/{id}")
    public ResponseEntity<UserResource> updateUserResouceInfo(@RequestBody @Valid UserResourceLibraryRequest request, @PathVariable int id){
        return ResponseEntity.ok().body(resourceService.updateUserResourceInfo(request, id));
    }

    
}
