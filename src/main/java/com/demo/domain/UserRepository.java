package com.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marc Schneider
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
