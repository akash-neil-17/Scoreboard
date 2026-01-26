package com.wlfscsg.GroupCamp.dao;

import com.wlfscsg.GroupCamp.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDao extends JpaRepository<Activity, Integer> {
}
