package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dao.PatrolDao;
import com.wlfscsg.GroupCamp.dto.PatrolSaveRequestDTO;
import com.wlfscsg.GroupCamp.entity.Patrol;
import com.wlfscsg.GroupCamp.model.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatrolServiceImplementation implements PatrolService{

    private final PatrolDao patrolDao;

    public PatrolServiceImplementation(PatrolDao patrolDao) {
        this.patrolDao = patrolDao;
    }

    @Override
    public CommonResponse savePatrolRequest(PatrolSaveRequestDTO saveModel) {
        Optional<Patrol> optionalPatrol = patrolDao.findByPatrolName(saveModel.getPatrolName());
        if (optionalPatrol.isPresent()){
            return CommonResponse.ALREADY_HAS;
        }
        else {
            Patrol patrol = new Patrol();
            patrol.setPatrolUnit(saveModel.getPatrolUnit());
            patrol.setPatrolName(saveModel.getPatrolName());
            try {
                patrolDao.save(patrol);
            } catch (Exception e) {
                log.info(e.getMessage());
                return CommonResponse.FAIL;
            }
            return CommonResponse.SUXS;
        }
    }

    @Override
    public List<Patrol> fetchPatrolList() {
        List<Patrol> patrolList = patrolDao.findAll();
        if (patrolList.isEmpty()){
            return Collections.emptyList();
        }
        return patrolList;
    }
}
