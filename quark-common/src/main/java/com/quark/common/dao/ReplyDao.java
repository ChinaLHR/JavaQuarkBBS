package com.quark.common.dao;

import com.quark.common.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author LHR
 * Create By 2017/8/20
 */
@Repository
public interface ReplyDao extends JpaRepository<Reply,Integer>{
}
