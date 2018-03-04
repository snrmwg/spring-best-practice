package com.demo.rest;

import com.demo.App;
import com.demo.domain.User;
import com.demo.domain.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Marc Schneider
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = App.class)
public class UsersEndpointTest {
  @Rule
  public MockitoRule rule = MockitoJUnit.rule()
    .strictness(Strictness.STRICT_STUBS);

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;

  @MockBean
  private UserService userService;

  @Before
  public void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(this.wac)
      .apply(springSecurity())
      .build();
  }

  @Test
  public void canCreateUser() throws Exception {
    String username = "Mustermann";

    User savedUser = new User(username).withId(122);
    given(userService.save(any(User.class))).willReturn(savedUser);

    mvc.perform(post("/api/users").param("name", username)
    //.with(httpBasic("user", "password"))
                .with(user("someone"))
    )
      .andExpect(status().isCreated())
      .andExpect(header().string("location", "http://localhost/api/users/122"))
      .andExpect(jsonPath("$.id").value(122))
      .andExpect(jsonPath("$.name").value(username));
  }

  @Test
  public void findAllUsers() throws Exception {
    List<User> allUsers = asList(new User("user1").withId(101), new User("user2").withId(102));
    given(userService.findAll()).willReturn(allUsers);

    mvc.perform(get("/api/users"))
      .andExpect(status().isOk())
      .andExpect(content().json(
        "[\n" + "  {\n" + "    \"id\": 101,\n" + "    \"name\": \"user1\"\n" + "  },\n" + "  {\n" + "    \"id\": 102,\n"
          + "    \"name\": \"user2\"\n" + "  }\n" + "]"));
  }
}
