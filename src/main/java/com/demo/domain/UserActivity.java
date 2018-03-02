package com.demo.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Marc Schneider
 */
@Entity
public class UserActivity {
  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne()
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  public UserActivity(User user) {
    Objects.requireNonNull(user);
    this.user = user;
  }

  private UserActivity() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
