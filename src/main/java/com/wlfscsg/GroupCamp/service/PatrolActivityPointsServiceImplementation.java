package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dao.ActivityDao;
import com.wlfscsg.GroupCamp.dao.PatrolActivityPointsDao;
import com.wlfscsg.GroupCamp.dao.PatrolDao;
import com.wlfscsg.GroupCamp.dto.PatrolActivityPointSaveDTO;
import com.wlfscsg.GroupCamp.entity.Activity;
import com.wlfscsg.GroupCamp.entity.Patrol;
import com.wlfscsg.GroupCamp.entity.PatrolActivityPoints;
import com.wlfscsg.GroupCamp.model.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatrolActivityPointsServiceImplementation implements PatrolActivityPointsService{

    private final PatrolDao patrolDao;

    private final ActivityDao activityDao;

    private final PatrolActivityPointsDao patrolActivityPointsDao;

    public PatrolActivityPointsServiceImplementation(PatrolDao patrolDao, ActivityDao activityDao, PatrolActivityPointsDao patrolActivityPointsDao) {
        this.patrolDao = patrolDao;
        this.activityDao = activityDao;
        this.patrolActivityPointsDao = patrolActivityPointsDao;
    }

    @Override
    public int activityPoint(String activityName) {
        Optional<Activity> optionalActivity = activityDao.findByActivityName(activityName);
        int allottedPoint = 0;
        if (optionalActivity.isPresent()){
            allottedPoint = optionalActivity.get().getPoint();
        }
        return allottedPoint;
    }

    @Override
    public CommonResponse savePatrolPointRequest(PatrolActivityPointSaveDTO saveDTO) {
        PatrolActivityPoints patrolActivityPoints = new PatrolActivityPoints();
        Optional<Activity> optionalActivity = activityDao.findByActivityName(saveDTO.getActivityName());
        Optional<Patrol> optionalPatrol = patrolDao.findByPatrolName(saveDTO.getPatrolName());
        if (optionalActivity.isPresent() && optionalPatrol.isPresent()) {
            Optional<PatrolActivityPoints> optionalPatrolActivityPoints = patrolActivityPointsDao.findByPatrolIdAndActivityId(optionalPatrol.get().getPatrolId(), optionalActivity.get().getActivityId());
            if (optionalPatrolActivityPoints.isPresent()) {
                return CommonResponse.ALREADY_HAS;
            }
            patrolActivityPoints.setPatrolId(optionalPatrol.get().getPatrolId());
            patrolActivityPoints.setActivityId(optionalActivity.get().getActivityId());
            patrolActivityPoints.setPointsEarned(saveDTO.getEarnedPoint());
            patrolActivityPoints.setCompletedAt(LocalDateTime.now());
            try{
                patrolActivityPointsDao.save(patrolActivityPoints);
            }catch (Exception e){
                log.info(e.getMessage());
                return CommonResponse.FAIL;
            }
        }
        return CommonResponse.SUXS;
    }

    @Override
    public List<PatrolActivityPoints> fetchPatrolActivityPoints() {
        List<PatrolActivityPoints> patrolActivityPointsList = patrolActivityPointsDao.findAll();
        if (patrolActivityPointsList.isEmpty()){
            return Collections.emptyList();
        }
        return patrolActivityPointsList;
    }
}
