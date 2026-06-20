package com.upm.lab10.service;

import com.upm.lab10.dto.UserDto;
import com.upm.lab10.model.Role;
import com.upm.lab10.model.User;
import com.upm.lab10.repository.RoleRepository;
import com.upm.lab10.repository.UserRepository;
import com.upm.lab10.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * PING WENCHAO 226969
 * UserServiceImpl - Concrete implementation of UserService handling transactional
 * user persistence, default role assignment, and secure BCrypt password encoding.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null) {
            role = roleRepository.save(new Role(TbConstants.Roles.USER));
        }

        // Instantiates a new persistent User, automatically encrypting the raw password payload
        User user = new User(
                userDto.getName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role)
        );

        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}