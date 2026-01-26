package com.wlfscsg.GroupCamp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patrol_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patrol {

    @Id
    @Column(name = "PATROL_ID")
    private Integer patrolId;

    @Column(name = "PATROL_UNIT")
    private Integer patrolUnit;

    @Column(name = "PATROL_NAME")
    private String patrolName;

}
