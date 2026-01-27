package com.wlfscsg.GroupCamp.dao;

import com.wlfscsg.GroupCamp.entity.Patrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatrolDao extends JpaRepository<Patrol, Integer> {
    Optional<Patrol> findByPatrolName(String patrolName);

}
