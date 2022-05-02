package com.wuahhh.userservice.service.impl;

import com.wuahhh.userservice.domain.OaUser;
import com.wuahhh.userservice.domain.Role;
import com.wuahhh.userservice.repo.RoleRepo;
import com.wuahhh.userservice.repo.UserRepo;
import com.wuahhh.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : hongqiangren.
 * @since: 2022/5/2 13:39
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OaUser saveUser(OaUser user) {
        log.info("Saving new user to db. user={}", user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role to db. role={}", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        OaUser user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public OaUser getUser(String username) {
        log.info("Getting user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<OaUser> getUsers() {
        log.info("Getting all users!");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OaUser oaUser = userRepo.findByUsername(username);
        if (oaUser == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database!");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        oaUser.getRoles().forEach(e -> authorities.add(new SimpleGrantedAuthority(e.getName())));
        User user = new User(oaUser.getUsername(), oaUser.getPassword(), authorities);
        return user;
    }
}
