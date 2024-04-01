package com.jtbc.jvt2.service.impl;


import com.jtbc.jvt2.entity.Role;
import com.jtbc.jvt2.entity.User;
import com.jtbc.jvt2.repo.RoleRepo;
import com.jtbc.jvt2.repo.UserRepo;
import com.jtbc.jvt2.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      com.jtbc.jvt2.entity.User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }



    @Override
    public com.jtbc.jvt2.entity.User saveUser(com.jtbc.jvt2.entity.User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }



    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        com.jtbc.jvt2.entity.User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public com.jtbc.jvt2.entity.User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<com.jtbc.jvt2.entity.User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public void blockUser(String username) {
     com.jtbc.jvt2.entity.User user = userRepo.findByUsername(username);
        if (user != null) {
            user.setBlocked(true);
            userRepo.save(user);
        }
    }
    @Override
    public void deleteUser(String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            userRepo.delete(user);
        }
    }
}