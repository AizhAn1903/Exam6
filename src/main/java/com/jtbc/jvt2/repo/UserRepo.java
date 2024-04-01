package com.jtbc.jvt2.repo;

import com.jtbc.jvt2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<com.jtbc.jvt2.entity.User, Long> {
    User findByUsername(String username);
}