package com.Learnig.security.springBootSecurity.security;

import com.Learnig.security.springBootSecurity.entities.UserEntity;
import com.Learnig.security.springBootSecurity.repositories.UserRepository;
import com.Learnig.security.springBootSecurity.security.rolesEnum.ApplicationRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.Learnig.security.springBootSecurity.security.rolesEnum.ApplicationRoles.ADMIN;
import static com.Learnig.security.springBootSecurity.security.rolesEnum.ApplicationRoles.USER;

@Service
public class CustomeUserDetailServices implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomeUserDetailServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " does not exist"));

        if (user.getRoles().equals("ADMIN")) {
            return new User(user.getUsername(),
                    user.getPassword(),
                    ADMIN.getGrantedAuthorities());
        }

        if (user.getRoles().equals("USER")) {
            return new User(user.getUsername(),
                    user.getPassword(),
                    USER.getGrantedAuthorities());
        }

        throw new UsernameNotFoundException("Username " + username + " does not exist");

    }
}