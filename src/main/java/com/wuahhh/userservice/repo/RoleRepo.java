package com.wuahhh.userservice.repo;

import com.wuahhh.userservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hongqiangren.
 * @since: 2022/5/2 13:35
 */
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(String name);


}
