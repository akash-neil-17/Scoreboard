package com.wlfscsg.GroupCamp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patrol_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patrol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATROL_ID")
    private Integer patrolId;

    @Column(name = "PATROL_UNIT")
    private int patrolUnit;

    @Column(name = "PATROL_NAME")
    private String patrolName;

}
