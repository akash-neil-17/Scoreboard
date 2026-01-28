package com.wlfscsg.GroupCamp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LeaderboardPatrolDTO {

    private Integer userId;
    private int patrolUnit;
    private String patrolName;
    private Integer totalPoints;
    private Integer leaderboardRank;

}
