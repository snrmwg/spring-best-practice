package com.demo.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 * @author Marc Schneider
 */
public class UserActivityTest {
  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(UserActivity.class)
      .withIgnoredFields("id")
      .verify();
  }
}
