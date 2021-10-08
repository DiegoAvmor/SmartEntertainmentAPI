package com.innercircle.api.service;

import java.util.List;

import com.innercircle.api.model.Resource;
import com.innercircle.api.model.ResourceType;
import com.innercircle.api.repository.ResourceRepository;
import com.innercircle.api.repository.ResourceTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ResourceTypeRepository resourceTypeRepository;

    public Page<Resource> searchResource(String name, Pageable pageable){
        return resourceRepository.findAllByNameContains(name, pageable);
    }

    public List<ResourceType> getResourceTypes(){
        return resourceTypeRepository.findAll();
    }
    
}
