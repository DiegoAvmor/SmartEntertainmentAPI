package com.innercircle.api.repository;

import com.innercircle.api.model.UserResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserResourceRepository extends JpaRepository<UserResource, Integer> {

    public boolean existsByUserIdAndResourceId(int user_id, int resource_id);

    public UserResource findByUserIdAndResourceId(int user_id, int resource_id);

    public Page<UserResource> findAllByUserId(int user_id, Pageable pageable);
}
