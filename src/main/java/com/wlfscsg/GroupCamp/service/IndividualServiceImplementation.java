package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dao.IndividualDao;
import com.wlfscsg.GroupCamp.dao.PatrolDao;
import com.wlfscsg.GroupCamp.dto.IndividualSaveRequestDTO;
import com.wlfscsg.GroupCamp.entity.Individual;
import com.wlfscsg.GroupCamp.entity.Patrol;
import com.wlfscsg.GroupCamp.model.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IndividualServiceImplementation implements IndividualService{

    private final IndividualDao individualDao;

    private final PatrolDao patrolDao;

    public IndividualServiceImplementation(IndividualDao individualDao, PatrolDao patrolDao) {
        this.individualDao = individualDao;
        this.patrolDao = patrolDao;
    }

    @Override
    public CommonResponse saveIndividualRequest(IndividualSaveRequestDTO saveModel) {
        Optional<Patrol> optionalPatrol = patrolDao.findByPatrolName(saveModel.getPatrolName());
        Optional<Individual> optionalIndividual = individualDao.findByIndividualNameAndMobileNumber(saveModel.getIndividualName(), saveModel.getMobileNumber());
        if (optionalIndividual.isPresent()){
            return CommonResponse.ALREADY_HAS;
        }
        if (optionalPatrol.isPresent()){
            Individual individual = new Individual();
            individual.setIndividualName(saveModel.getIndividualName());
            individual.setPatrolName(saveModel.getPatrolName());
            individual.setMobileNumber(saveModel.getMobileNumber());
            individual.setPatrolUnit(optionalPatrol.get().getPatrolUnit());
            try{
                individualDao.save(individual);
            }
            catch (Exception e){
                log.info(e.getMessage());
                return CommonResponse.FAIL;
            }
            return CommonResponse.SUXS;
        }else {
            return CommonResponse.FAIL;
        }
    }

    @Override
    public List<Individual> fetchIndividualList() {
        List<Individual> individualList = individualDao.findAll();
        if (individualList.isEmpty()){
            return Collections.emptyList();
        }
        return individualList;
    }
}
