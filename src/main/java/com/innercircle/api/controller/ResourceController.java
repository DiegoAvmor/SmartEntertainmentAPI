package com.innercircle.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.innercircle.api.model.Resource;
import com.innercircle.api.model.ResourceType;
import com.innercircle.api.model.UserResource;
import com.innercircle.api.model.request.UserResourceRequest;
import com.innercircle.api.service.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/resource/me/library")
    public ResponseEntity<UserResource> register(@RequestBody @Valid UserResourceRequest request){
        return ResponseEntity.ok().body(resourceService.addResourceToUserLibrary(request));
    }
    
}
