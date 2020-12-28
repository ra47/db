package com.alli.backend.services;

import com.alli.backend.models.Role;
import com.alli.backend.models.User;
import com.alli.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder(10, new SecureRandom());

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public void register(User user) throws Exception {
        if (!checkIfUserExist(user.getEmail())) {
            throw new Exception("User Existed!");
        }
        encodePassword(user);
        userRepo.save(user);
    }

    public User findByUsername(String user) {
        return userRepo.findByUsername(user).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findByRole(Role role) {
        return userRepo.findByRole(role);
    }

    //ideal is check email username citizen_id
    public boolean checkIfUserExist(String email) {
        return userRepo.findByEmail(email).isEmpty();
    }

    private void encodePassword(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
}

