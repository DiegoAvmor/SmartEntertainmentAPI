package com.innercircle.api.service;

import java.util.List;

import com.innercircle.api.model.Resource;
import com.innercircle.api.model.ResourceType;
import com.innercircle.api.model.Status;
import com.innercircle.api.model.User;
import com.innercircle.api.model.UserResource;
import com.innercircle.api.model.request.UserResourceRequest;
import com.innercircle.api.repository.ResourceRepository;
import com.innercircle.api.repository.ResourceTypeRepository;
import com.innercircle.api.repository.StatusRepository;
import com.innercircle.api.repository.UserResourceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ResourceTypeRepository resourceTypeRepository;

    @Autowired
    UserResourceRepository userResourceRepository;

    @Autowired
    StatusRepository statusRepository;

    public Page<Resource> searchResource(String name, Pageable pageable){
        return resourceRepository.findAllByNameContains(name, pageable);
    }

    public List<ResourceType> getResourceTypes(){
        return resourceTypeRepository.findAll();
    }

    public List<Status> getResourceStatus(){
        return statusRepository.findAll();
    }
    
    public UserResource addResourceToUserLibrary(UserResourceRequest request){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Si existe la entrada se procede a actualizarla
        UserResource userResource;
        if(userResourceRepository.existsByUserIdAndResourceId(user.getId(), request.getResource())){
            userResource = userResourceRepository.findByUserIdAndResourceId(user.getId(), request.getResource());
            userResource.setStatusId(request.getStatus());
            userResource.setFavorite(request.isFavorite());
            userResource.setStartedOn(request.getStarted_on());
            userResource.setFinishedOn(request.getFinished_on());
            userResource.setCurrentChapter(request.getCurrent_chapter());
        }else{
            //En caso contrario se crea la entrada
            userResource = new UserResource();
            userResource.setUserId(user.getId());
            userResource.setResourceId(request.getResource());
            userResource.setStatusId(request.getStatus());
            userResource.setFavorite(request.isFavorite());
            userResource.setStartedOn(request.getStarted_on());
            userResource.setFinishedOn(request.getFinished_on());
            userResource.setCurrentChapter(request.getCurrent_chapter());
        }

        userResourceRepository.save(userResource);

        return userResource;
    }
}
