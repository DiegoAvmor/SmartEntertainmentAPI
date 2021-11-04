package com.innercircle.api.service;

import java.util.List;
import java.util.Optional;

import com.innercircle.api.exceptions.AccessDeniedException;
import com.innercircle.api.exceptions.NotFoundException;
import com.innercircle.api.model.Resource;
import com.innercircle.api.model.ResourceType;
import com.innercircle.api.model.Status;
import com.innercircle.api.model.User;
import com.innercircle.api.model.UserResource;
import com.innercircle.api.model.request.UserResourceLibraryRequest;
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
        User user = getUserContext();
        //Si existe la entrada se procede a actualizarla
        UserResource userResource;
        if(userResourceRepository.existsByUserIdAndResourceId(user.getId(), request.getResource())){
            userResource = findInLibraryByUserIdAndResourceId(user.getId(), request.getResource());
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

    public UserResource findInUserLibraryByResourceId(int resourceId){
        User user = getUserContext();
        return findInLibraryByUserIdAndResourceId(user.getId(), resourceId);
    }

    public Page<UserResource> getCurrentUserLibrary(Pageable pageable){
        User user = getUserContext();
        return userResourceRepository.findAllByUserId(user.getId(), pageable);
    }

    public UserResource deleteResourceFromUserLibrary(int userResourceId){
        UserResource userResource = findInLibraryByUserResourceId(userResourceId);
        //Validamos si corresponde al usuario que hace la petición
        if(!checkIfBelongsToUser(userResource.getUserId())){
            throw new AccessDeniedException();
        }
        userResourceRepository.delete(userResource);
        return userResource;
    }

    public UserResource updateUserResourceInfo(UserResourceLibraryRequest request, int userResourceId){
        UserResource userResource = findInLibraryByUserResourceId(userResourceId);
        //Validamos si corresponde al usuario que hace la petición
        if(!checkIfBelongsToUser(userResource.getUserId())){
            throw new AccessDeniedException();
        }
        userResource.setStatusId(request.getStatus());
        userResource.setFavorite(request.isFavorite());
        userResource.setStartedOn(request.getStarted_on());
        userResource.setFinishedOn(request.getFinished_on());
        userResource.setCurrentChapter(request.getCurrent_chapter());
        userResourceRepository.save(userResource);
        return userResource;
    }

    private UserResource findInLibraryByUserIdAndResourceId(int userId, int resourceId){
        UserResource  userResource = userResourceRepository.findByUserIdAndResourceId(userId, resourceId);
        if(userResource == null){
            throw new NotFoundException();
        }
        return userResource;
    }

    private UserResource findInLibraryByUserResourceId(int userResourceId){
        Optional<UserResource>  userResource = userResourceRepository.findById(userResourceId);
        if(!userResource.isPresent()){
            throw new NotFoundException();
        }
        return userResource.get();
    }

    private boolean checkIfBelongsToUser(int userId){
        User user = getUserContext();
        return user.getId().equals(userId);
    }

    private User getUserContext(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
