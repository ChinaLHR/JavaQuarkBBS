package com.quark.common.dao;

import com.quark.common.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserDao extends JpaRepository<User,Integer> ,JpaSpecificationExecutor {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query(value = "select u.id, u.username , u.icon from quark_user u where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <=DATE(u.init_time) ORDER BY u.id DESC limit 12" ,nativeQuery = true)
    List<Object> findNewUser();

}
