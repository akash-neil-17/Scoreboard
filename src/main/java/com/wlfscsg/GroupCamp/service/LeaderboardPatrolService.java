package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dto.LeaderboardPatrolDTO;

import java.util.List;

public interface LeaderboardPatrolService {

    List<LeaderboardPatrolDTO> getLeaderboard();
    List<LeaderboardPatrolDTO> getTop10Leaderboard();

}
