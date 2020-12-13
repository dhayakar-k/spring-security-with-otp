package com.springboot.springsecurityotp.repositories;

import com.springboot.springsecurityotp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>,
        PagingAndSortingRepository<User, String> {

    User getByUserName(String userName);
}
