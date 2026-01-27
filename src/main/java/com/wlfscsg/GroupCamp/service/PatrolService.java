package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dto.PatrolSaveRequestDTO;
import com.wlfscsg.GroupCamp.entity.Patrol;
import com.wlfscsg.GroupCamp.model.CommonResponse;

import java.util.List;

public interface PatrolService {
    CommonResponse savePatrolRequest(PatrolSaveRequestDTO saveModel);

    List<Patrol> fetchPatrolList();
}
