package com.api.dl.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
  
  Optional<User> findById (String id);
  Optional<User> findByUsername (String username);

}