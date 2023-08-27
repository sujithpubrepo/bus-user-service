package com.user.bususerservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserid(String userid);
}