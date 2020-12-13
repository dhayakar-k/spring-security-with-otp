package com.springboot.springsecurityotp.repositories;

import com.springboot.springsecurityotp.models.UserActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepository extends MongoRepository<UserActivity, String>,
        PagingAndSortingRepository<UserActivity, String> {
}
