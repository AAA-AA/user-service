package com.wuahhh.userservice.repo;

import com.wuahhh.userservice.domain.OaUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hongqiangren.
 * @since: 2022/5/2 13:33
 */
public interface UserRepo extends JpaRepository<OaUser, Long> {

    OaUser findByUsername(String username);


}
