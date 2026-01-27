package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dto.ActivitySaveRequestDTO;
import com.wlfscsg.GroupCamp.entity.Activity;
import com.wlfscsg.GroupCamp.model.CommonResponse;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    CommonResponse saveActivityRequest (ActivitySaveRequestDTO saveModel);
    List<Activity> fetchActivityList();
}
