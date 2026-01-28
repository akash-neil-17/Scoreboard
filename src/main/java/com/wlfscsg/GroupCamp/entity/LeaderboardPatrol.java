package com.wlfscsg.GroupCamp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leaderboard")
@Getter
@NoArgsConstructor
public class LeaderboardPatrol {

    @Id
    @Column(name = "patrol_id")
    private Integer patrolId;

    @Column(name = "patrol_unit")
    private int patrolUnit;

    @Column(name = "patrol_name")
    private String patrolName;

    @Column(name = "total_points")
    private Integer totalPoints;

    @Column(name = "leaderboard_rank")
    private Integer leaderboardRank;
}
