package com.wlfscsg.GroupCamp.dao;

import com.wlfscsg.GroupCamp.entity.PatrolActivityPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatrolActivityPointsDao extends JpaRepository<PatrolActivityPoints, Integer> {

    Optional<PatrolActivityPoints> findByPatrolIdAndActivityId (int patrolId, int activityId);

}
