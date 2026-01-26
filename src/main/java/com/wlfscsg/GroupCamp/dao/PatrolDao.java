package com.wlfscsg.GroupCamp.dao;

import com.wlfscsg.GroupCamp.entity.Patrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrolDao extends JpaRepository<Patrol, Integer> {
}
