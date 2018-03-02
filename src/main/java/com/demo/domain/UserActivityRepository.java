package com.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Marc Schneider
 */
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
  List<UserActivity> findByUser(User user);
}
