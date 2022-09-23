package com.example.order1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class User {
    Long userId;
    String firstName;
    String lastName;
    String address;
    String emailAddress;
    LocalDate DOB;
    String password;
}
