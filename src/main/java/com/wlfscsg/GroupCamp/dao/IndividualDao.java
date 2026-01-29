package com.wlfscsg.GroupCamp.dao;

import com.wlfscsg.GroupCamp.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualDao extends JpaRepository<Individual, Integer> {
}
