package com.quark.common.dao;

import com.quark.common.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectDao extends JpaRepository<Collect,Integer> {
}
