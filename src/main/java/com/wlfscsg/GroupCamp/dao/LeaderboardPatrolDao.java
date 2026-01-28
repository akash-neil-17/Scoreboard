package com.wlfscsg.GroupCamp.dao;

import com.wlfscsg.GroupCamp.entity.LeaderboardPatrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardPatrolDao extends JpaRepository<LeaderboardPatrol, Integer> {

    List<LeaderboardPatrol> findAllByOrderByLeaderboardRankAsc();

    List<LeaderboardPatrol> findTop10ByOrderByLeaderboardRankAsc();

}
