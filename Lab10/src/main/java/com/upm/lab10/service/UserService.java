package com.upm.lab10.service;

import com.upm.lab10.dto.UserDto;
import com.upm.lab10.model.User;

/**
 * PING WENCHAO 226969
 * UserService - Interface defining core contractual operations for user lifecycle management.
 */
public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}