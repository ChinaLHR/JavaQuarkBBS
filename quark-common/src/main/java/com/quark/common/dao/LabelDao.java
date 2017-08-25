package com.quark.common.dao;

import com.quark.common.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelDao extends JpaRepository<Label,Integer> {
}
