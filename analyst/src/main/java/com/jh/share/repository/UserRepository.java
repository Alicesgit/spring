package com.jh.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jh.share.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
 
}
