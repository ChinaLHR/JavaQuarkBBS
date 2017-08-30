package com.quark.common.dao;

import com.quark.common.entity.Reply;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author LHR
 * Create By 2017/8/20
 */
@CacheConfig(cacheNames = "replies")
@Repository
public interface ReplyDao extends JpaRepository<Reply,Integer>,JpaSpecificationExecutor {

    @Cacheable
    @Override
    List<Reply> findAll();
}
