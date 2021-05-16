package com.gimeast.myboard.service;

import com.gimeast.myboard.model.Role;
import com.gimeast.myboard.model.User;
import com.gimeast.myboard.repository.RoleRepository;
import com.gimeast.myboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        Role role = roleRepository.findByName("ROLE_USER");
        role.setId(role.getId());
        user.getRoles().add(role);

        return userRepository.save(user);
    }
}
