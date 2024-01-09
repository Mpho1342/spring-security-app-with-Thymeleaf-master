package com.Learnig.security.springBootSecurity.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @SequenceGenerator
            (
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
            )
    @GeneratedValue
            (
            generator = "user_id_seq",
            strategy = GenerationType.SEQUENCE
            )
    private Integer userId;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

   @Column(nullable = false)
    private String roles;


}
