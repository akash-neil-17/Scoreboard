package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dao.ActivityDao;
import com.wlfscsg.GroupCamp.dto.ActivitySaveRequestDTO;
import com.wlfscsg.GroupCamp.entity.Activity;
import com.wlfscsg.GroupCamp.model.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ActivityServiceImplementation implements ActivityService{

    private final ActivityDao activityDao;

    public ActivityServiceImplementation(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    @Override
    public CommonResponse saveActivityRequest(ActivitySaveRequestDTO saveModel) {
        Optional<Activity> optionalActivity = activityDao.findByActivityName(saveModel.getActivityName());
        if (optionalActivity.isPresent()){
            return CommonResponse.ALREADY_HAS;
        }
        else {
            Activity activity = new Activity();
            activity.setActivityName(saveModel.getActivityName());
            activity.setPoint(saveModel.getPoint());
            try {
                activityDao.save(activity);
            } catch (Exception e) {
                log.info(e.getMessage());
                return CommonResponse.FAIL;
            }
            return CommonResponse.SUXS;
        }
    }

    @Override
    public List<Activity> fetchActivityList() {
        List<Activity> activityList = activityDao.findAll();
        if (activityList.isEmpty()){
            return Collections.emptyList();
        }
        return activityList;
    }
}
