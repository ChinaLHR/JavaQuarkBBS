package com.quark.common.dao;

import com.quark.common.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsDao extends JpaRepository<Posts,Integer> {
}
