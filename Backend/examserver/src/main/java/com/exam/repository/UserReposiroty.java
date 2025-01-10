package com.exam.repository;

import com.exam.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserReposiroty extends JpaRepository<User , Long> {


    @Query("SELECT u FROM User u WHERE u.username=:username")
    User findByName(@Param("username") String username);
}
