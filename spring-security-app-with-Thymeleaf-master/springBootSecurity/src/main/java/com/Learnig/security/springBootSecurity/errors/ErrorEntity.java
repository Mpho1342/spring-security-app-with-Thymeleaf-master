package com.Learnig.security.springBootSecurity.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorEntity {
    public String message;
    public LocalDateTime dateTime;
}
