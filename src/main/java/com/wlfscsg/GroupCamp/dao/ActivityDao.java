package com.wlfscsg.GroupCamp.dao;

import com.wlfscsg.GroupCamp.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityDao extends JpaRepository<Activity, Integer> {
    Optional<Activity> findByActivityName(String activityName);
}
