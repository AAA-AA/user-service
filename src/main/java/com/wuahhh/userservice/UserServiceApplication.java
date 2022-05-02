package com.wuahhh.userservice;

import com.wuahhh.userservice.domain.OaUser;
import com.wuahhh.userservice.domain.Role;
import com.wuahhh.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * @author : hongqiangren.
 * @since: 2022/5/2 13:26
 */
@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new OaUser(null, "John","john", "john@wuahhh.com","1234", new ArrayList<>()));

            userService.saveUser(new OaUser(null, "Jim","jim", "jim@wuahhh.com","1234", new ArrayList<>()));
            userService.saveUser(new OaUser(null, "Will Smith","will", "will@wuahhh.com","1234", new ArrayList<>()));

            userService.addRoleToUser("john","ROLE_USER");
            userService.addRoleToUser("jim","ROLE_ADMIN");
            userService.addRoleToUser("will","ROLE_SUPER_ADMIN");
            userService.addRoleToUser("will","ROLE_MANAGER");
            userService.addRoleToUser("will","ROLE_USER");
        };
    }


}
