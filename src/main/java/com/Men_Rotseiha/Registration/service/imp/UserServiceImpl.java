package com.Men_Rotseiha.Registration.service.imp;

import com.Men_Rotseiha.Registration.entity.User;
import com.Men_Rotseiha.Registration.repository.UserRepository;
import com.Men_Rotseiha.Registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User SaveUser(User user) {
        return userRepository.save(user);
    }

    public boolean registerUser(User user) {
        Optional<User> foundUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (foundUser.isPresent()) {
            return false; // User already exists
        }
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean authenticateUser(User user) {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser.isPresent() && passwordEncoder.matches(user.getPassword(), foundUser.get().getPassword())) {
            return true;
        }
        return false;
    }

}
