package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dto.PatrolActivityPointSaveDTO;
import com.wlfscsg.GroupCamp.entity.PatrolActivityPoints;
import com.wlfscsg.GroupCamp.model.CommonResponse;

import java.util.List;

public interface PatrolActivityPointsService {
    int activityPoint (String activityName);
    CommonResponse savePatrolPointRequest(PatrolActivityPointSaveDTO saveDTO);
    List<PatrolActivityPoints> fetchPatrolActivityPoints ();
}
