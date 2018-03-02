package com.demo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Marc Schneider
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Import(UserService.class)
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void canDeleteUserWithActivities() {
    User user = new User();
    user = userService.save(user);

    UserActivity activity1 = new UserActivity(user);
    userService.save(activity1);

    UserActivity activity2 = new UserActivity(user);
    userService.save(activity2);

    List<UserActivity> activities = userService.findActivitiesFor(user);
    assertThat(activities).hasSize(2);

    userService.delete(user);
    activities = userService.findActivitiesFor(user);
    assertThat(activities).isEmpty();
  }
}
