package com.alli.backend.services;

import com.alli.backend.models.User;
import com.alli.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder(10, new SecureRandom());

    public void register(User user) throws Exception {

        //Let's check if user already registered with us
        if (!checkIfUserExist(user.getEmail())) {
            throw new Exception();
        }
        encodePassword(user);
        userRepo.save(user);
    }

    public boolean checkIfUserExist(String email) {
        return userRepo.findByEmail(email).isEmpty();
    }

    private void encodePassword(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
}

