package com.api.dl.security;

import java.util.Optional;

import com.api.dl.user.User;
import com.api.dl.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserInfoService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<User> optUser = userRepository.findByUsername(username);

    if (!optUser.isPresent()) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    User user = optUser.get();

    return org.springframework.security.core.userdetails.User
          .withUsername(username)
          .password(user.getPassword())
          .authorities(user.getRoles())
          .accountExpired(false)
          .accountLocked(false)
          .credentialsExpired(false)
          .disabled(false)
          .build();
  }

}