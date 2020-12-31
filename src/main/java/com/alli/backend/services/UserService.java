package com.alli.backend.services;

import com.alli.backend.models.Role;
import com.alli.backend.models.User;
import com.alli.backend.payload.JwtResponse;
import com.alli.backend.payload.LoginRequest;
import com.alli.backend.repositories.UserRepository;
import com.alli.backend.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;


//    BCryptPasswordEncoder bCryptPasswordEncoder =
//            new BCryptPasswordEncoder(10, new SecureRandom());

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public void register(User user) throws Exception {
        if(checkUsername(user.getUsername()) && checkCitizenId(user.getCitizenId()) && checkEmail(user.getEmail())){
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
        }else{
            throw new RuntimeException("Duplicated");
        }
    }

    public JwtResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.joining());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),role);
    }

    public boolean checkUsername(String username){
        return userRepo.findByUsername(username).isEmpty();
    }

    public boolean checkEmail(String email){
        return userRepo.findByEmail(email).isEmpty();
    }

    public boolean checkCitizenId(String citizenId){
        return userRepo.findByCitizenId(citizenId).isEmpty();
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findByRole(Role role) {
        return userRepo.findByRole(role);
    }
}

