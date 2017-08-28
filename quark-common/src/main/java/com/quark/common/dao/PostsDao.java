package com.quark.common.dao;

import com.quark.common.entity.Posts;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@CacheConfig(cacheNames = "postses")
@Repository
public interface PostsDao extends JpaRepository<Posts,Integer> ,JpaSpecificationExecutor {

    @Cacheable
    @Override
    List<Posts> findAll();

}
