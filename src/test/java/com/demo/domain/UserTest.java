package com.demo.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 * @author Marc Schneider
 */
public class UserTest {
  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(User.class)
      .withIgnoredFields("id")
      .verify();
  }
}
