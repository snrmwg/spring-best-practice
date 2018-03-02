package com.demo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marc Schneider
 */
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
  List<UserActivity> findByUser(User user);
}
