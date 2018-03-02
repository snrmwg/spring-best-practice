package com.demo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marc Schneider
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserActivityRepository userActivityRepository;

  public User save(User user) {
    return userRepository.save(user);
  }

  public UserActivity save(UserActivity userActivity) {
    return userActivityRepository.save(userActivity);
  }

  public List<UserActivity> findActivitiesFor(User user) {
    return userActivityRepository.findByUser(user);
  }

  public void delete(User user) {
    userRepository.delete(user);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }
}
