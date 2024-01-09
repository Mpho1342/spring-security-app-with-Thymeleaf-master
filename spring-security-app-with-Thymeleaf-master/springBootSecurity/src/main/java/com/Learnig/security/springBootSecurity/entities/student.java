package com.Learnig.security.springBootSecurity.entities;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class student {
    public String name;
    public String role;
    public Integer age;
}
