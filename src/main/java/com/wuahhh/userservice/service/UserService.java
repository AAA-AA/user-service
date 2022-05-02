package com.wuahhh.userservice.service;

import com.wuahhh.userservice.domain.OaUser;
import com.wuahhh.userservice.domain.Role;

import java.util.List;

/**
 * @author : hongqiangren.
 * @since: 2022/5/2 13:36
 */
public interface UserService {
    OaUser saveUser(OaUser user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    OaUser getUser(String username);

    List<OaUser> getUsers();



}
