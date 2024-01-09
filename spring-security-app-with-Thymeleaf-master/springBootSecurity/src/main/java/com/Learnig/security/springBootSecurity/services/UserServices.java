package com.Learnig.security.springBootSecurity.services;

import com.Learnig.security.springBootSecurity.entities.UserEntity;
import com.Learnig.security.springBootSecurity.repositories.RolesRepositoy;
import com.Learnig.security.springBootSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private UserRepository userRepository;
    private RolesRepositoy rolesRepositoy;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    @Autowired
    public UserServices(UserRepository userRepository, RolesRepositoy rolesRepositoy,
                        PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.rolesRepositoy = rolesRepositoy;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }



    //register user
    public UserEntity registerUser(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return userRepository.findById(user.getUserId()).get();
    }

    //login user
    public void loginUser(UserEntity user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
