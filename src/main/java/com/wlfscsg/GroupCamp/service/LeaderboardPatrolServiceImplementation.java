package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dao.LeaderboardPatrolDao;
import com.wlfscsg.GroupCamp.dto.LeaderboardPatrolDTO;
import com.wlfscsg.GroupCamp.entity.LeaderboardPatrol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LeaderboardPatrolServiceImplementation implements LeaderboardPatrolService{

    private final LeaderboardPatrolDao leaderboardPatrolDao;

    public LeaderboardPatrolServiceImplementation(LeaderboardPatrolDao leaderboardPatrolDao) {
        this.leaderboardPatrolDao = leaderboardPatrolDao;
    }

    @Override
    public List<LeaderboardPatrolDTO> getLeaderboard() {
        return leaderboardPatrolDao.findAllByOrderByLeaderboardRankAsc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaderboardPatrolDTO> getTop10Leaderboard() {
        return leaderboardPatrolDao.findTop10ByOrderByLeaderboardRankAsc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private LeaderboardPatrolDTO toDTO(LeaderboardPatrol leaderboardPatrol) {
        return new LeaderboardPatrolDTO(
                leaderboardPatrol.getPatrolId(),
                leaderboardPatrol.getPatrolUnit(),
                leaderboardPatrol.getPatrolName(),
                leaderboardPatrol.getTotalPoints(),
                leaderboardPatrol.getLeaderboardRank()
        );
    }

}
