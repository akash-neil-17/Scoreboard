package com.wlfscsg.GroupCamp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatrolActivityPointSaveDTO {

    private List<String> patrolList;
    private String patrolName;
    private List<String> activityList;
    private String activityName;
    private int earnedPoint;

}
