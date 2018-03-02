package com.demo.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Marc Schneider
 */
@Entity
public final class UserActivity {
  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne()
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @Column
  private String description;

  public UserActivity(User user, String description) {
    Objects.requireNonNull(user);
    Objects.requireNonNull(description);
    this.user = user;
    this.description = description;
  }

  protected UserActivity() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserActivity activity = (UserActivity) o;
    return Objects.equals(user, activity.user) && Objects.equals(description, activity.description);
  }

  @Override
  public int hashCode() {

    return Objects.hash(user, description);
  }
}
