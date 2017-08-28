package com.quark.common.dao;

import com.quark.common.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserDao extends JpaRepository<User,Integer> ,JpaSpecificationExecutor {

    User findByUsername(String username);

    User findByEmail(String email);



}
