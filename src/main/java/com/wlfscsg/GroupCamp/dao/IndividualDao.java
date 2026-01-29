package com.wlfscsg.GroupCamp.dao;

import com.wlfscsg.GroupCamp.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualDao extends JpaRepository<Individual, Integer> {
    Optional<Individual> findByIndividualNameAndMobileNumber(String individualName, String mobileNumber);
}
