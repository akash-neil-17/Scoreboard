package com.wlfscsg.GroupCamp.service;

import com.wlfscsg.GroupCamp.dto.IndividualSaveRequestDTO;
import com.wlfscsg.GroupCamp.entity.Individual;
import com.wlfscsg.GroupCamp.model.CommonResponse;

import java.util.List;

public interface IndividualService {

    CommonResponse saveIndividualRequest (IndividualSaveRequestDTO saveModel);

    List<Individual> fetchIndividualList ();

}
