package com.wlfscsg.GroupCamp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activity_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;

    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @Column(name = "ACTIVITY_POINT")
    private double point;

}
