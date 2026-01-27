package com.wlfscsg.GroupCamp.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;

    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @Column(name = "ACTIVITY_POINT")
    private int point;

}
