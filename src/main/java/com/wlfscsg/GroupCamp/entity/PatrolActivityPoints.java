package com.wlfscsg.GroupCamp.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_activity_points")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatrolActivityPoints {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PATROL_ID")
    private int patrolId;

    @Column(name = "ACTIVITY_ID")
    private int activityId;

    @Column(name = "POINTS_EARNED")
    private double pointsEarned;

    @Column(name = "COMPLETED_AT")
    private LocalDateTime completedAt;

}
