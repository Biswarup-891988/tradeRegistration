package com.reg.service;

import java.util.List;
import java.util.Optional;
import com.reg.entity.User;
import com.reg.exception.PasswordException;
import com.reg.exception.UserNotFoundException;
import com.reg.repo.UserRepository;

public class RegistrationService {

  private final UserRepository userRepository;

  public RegistrationService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public void validateUser(String userName, String password) throws Exception {

    Optional<User> user = userRepository.findByName(userName);
    if (user.isEmpty()) {
      throw new UserNotFoundException("Invalid User");
    } else {
      User u = user.get();
      if (!password.equals(u.getPassword())) {
        throw new PasswordException("Invalid Password");
      }
    }

  }

  public List<User> getUserList() {
    return userRepository.findAll();
  }

  public User getUserInfo(String userName) throws Exception {
    Optional<User> user = userRepository.findByName(userName);
    if (user.isEmpty()) {
      throw new UserNotFoundException("Invalid User");
    } else {
      return user.get();
    }
  }



}
