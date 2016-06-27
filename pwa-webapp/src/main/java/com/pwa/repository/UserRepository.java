package com.pwa.repository;

import com.pwa.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends BaseEntityRepository<User> {

    @Query("SELECT u FROM User u WHERE lower(u.username) = lower(:username)")
    User findUserByUsername(@Param("username") String username);

}

