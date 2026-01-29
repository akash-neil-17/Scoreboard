package com.wlfscsg.GroupCamp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "individual_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Individual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer individualId;

    @Column(name = "NAME")
    private String individualName;

    @Column(name = "PATROL_UNIT")
    private int patrolUnit;

    @Column(name = "PATROL_NAME")
    private String patrolName;

    @Column(name = "CONTACT")
    private String mobileNumber;

}
