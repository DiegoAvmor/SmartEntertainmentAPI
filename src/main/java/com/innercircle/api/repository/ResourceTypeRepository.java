package com.innercircle.api.repository;

import com.innercircle.api.model.ResourceType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceTypeRepository extends JpaRepository<ResourceType, Integer>{
    
}
