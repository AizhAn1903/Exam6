package com.jtbc.jvt2.repo;


import com.jtbc.jvt2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}