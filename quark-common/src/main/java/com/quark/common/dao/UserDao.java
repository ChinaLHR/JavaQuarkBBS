package com.quark.common.dao;

import com.quark.common.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserDao extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}
