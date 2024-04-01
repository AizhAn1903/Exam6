package com.jtbc.jvt2.service;


import com.jtbc.jvt2.entity.Role;
import com.jtbc.jvt2.entity.User;

import java.util.List;

public interface UserService {
   User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);
  User getUser(String username);

    List<User> getUsers();

    void blockUser(String username);

    void deleteUser(String username);
}