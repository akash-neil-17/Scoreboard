package com.wlfscsg.GroupCamp.dao;

import com.wlfscsg.GroupCamp.entity.PatrolActivityPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatrolActivityPointsDao extends JpaRepository<PatrolActivityPoints, Integer> {

    Optional<PatrolActivityPoints> findByPatrolIdAndActivityId (int patrolId, int activityId);

    @Query(value = "SELECT COALESCE(max(uap.OCCURRENCE_NO), 0) + 1 FROM user_activity_points uap WHERE uap.PATROL_ID=?1 AND uap.ACTIVITY_ID=?2", nativeQuery = true)
    int findNextOccurrence(Integer patrolId, Integer activityId);
}
