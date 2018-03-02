package com.demo.rest;

import com.demo.domain.User;
import com.demo.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

/**
 * @author Marc Schneider
 */
@RestController
@RequestMapping("/api/users")
public class UsersEndpoint {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<User> create(Principal principal, @RequestParam String name) {
    Objects.requireNonNull(principal, "authenticated caller is required");
    User user = new User(name);

    User savedUser = userService.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(savedUser.getId())
      .toUri();
    return ResponseEntity.created(location)
      .body(savedUser);
  }

  @GetMapping
  public List<User> findAll() {
    return userService.findAll();
  }
}
