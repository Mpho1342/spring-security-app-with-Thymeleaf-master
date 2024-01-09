package com.Learnig.security.springBootSecurity.repositories;

import com.Learnig.security.springBootSecurity.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    public UserRepository userRepository;


    @MockBean
    public TestEntityManager entityManager;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        user = UserEntity.builder()
                .userId(1)
                .roles("ADMIN")
                .username("Thabo")
                .password("22Mthabo")
                .build();

        entityManager.persist(user);
    }

    @Test
    void findByUsername() {
        UserEntity user1 = userRepository.findByUsername("Thabo").orElseThrow(()->new RuntimeException("not found"));

        assertEquals("Thabo", user1.getUsername());
    }
}