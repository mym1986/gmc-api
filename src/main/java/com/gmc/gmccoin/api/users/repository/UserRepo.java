package com.gmc.gmccoin.api.users.repository;


import com.gmc.gmccoin.common.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepository
 */

@Repository
public interface UserRepo extends JpaRepository<User, Long>, UserRepoEx {

    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email")String email);


    List<User> findByRecommenderOrderByCreatedAtDesc(String email);

    User findByUserId(String userId);
}