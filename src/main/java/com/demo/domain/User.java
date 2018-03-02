package com.demo.domain;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author Marc Schneider
 */
@Entity
public final class User {
  @Id
  @GeneratedValue
  private Integer id;

  @Column(unique = true, nullable = false)
  private String name;

  protected User() {
  }

  public User(String name) {

    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }

  public User withId(int id) {
    this.id = id;
    return this;
  }
}
