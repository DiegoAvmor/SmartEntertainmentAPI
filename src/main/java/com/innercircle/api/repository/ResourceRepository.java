package com.innercircle.api.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.innercircle.api.model.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Integer>{

    Page<Resource> findAllByNameContains(String name, Pageable pageable);
    
}
