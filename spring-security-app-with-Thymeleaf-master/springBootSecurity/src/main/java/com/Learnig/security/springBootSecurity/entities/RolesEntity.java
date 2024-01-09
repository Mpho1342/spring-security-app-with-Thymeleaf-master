package com.Learnig.security.springBootSecurity.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesEntity {

    @Id
    private Integer roleId;

    private String roleName;
}
