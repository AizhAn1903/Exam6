package com.jtbc.jvt2;

import com.jtbc.jvt2.entity.Role;
import com.jtbc.jvt2.entity.User;
import com.jtbc.jvt2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;


@SpringBootApplication
public class Jvt2Application {

    public static void main(String[] args) {
        SpringApplication.run(Jvt2Application.class, args);
    }
    @Bean
    CommandLineRunner run(UserService userService) {
        return arg -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "admin", "admin", "admin", false,new ArrayList<>()));
            userService.saveUser(new User(null, "user1", "user1", "user1",false, new ArrayList<>()));
            userService.saveUser(new User(null, "user2", "user2", "user2",false, new ArrayList<>()));


            userService.addRoleToUser("user2", "ROLE_USER");
            userService.addRoleToUser("user1", "ROLE_USER");
            userService.addRoleToUser("admin", "ROLE_SUPER_ADMIN");

        };
    }


}
