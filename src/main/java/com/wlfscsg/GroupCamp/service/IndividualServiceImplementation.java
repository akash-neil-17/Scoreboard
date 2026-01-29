package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dao.IndividualDao;
import com.wlfscsg.GroupCamp.dto.IndividualSaveRequestDTO;
import com.wlfscsg.GroupCamp.entity.Individual;
import com.wlfscsg.GroupCamp.model.CommonResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class IndividualServiceImplementation implements IndividualService{

    private final IndividualDao individualDao;

    public IndividualServiceImplementation(IndividualDao individualDao) {
        this.individualDao = individualDao;
    }

    @Override
    public CommonResponse saveIndividualRequest(IndividualSaveRequestDTO saveModel) {
        return null;
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
