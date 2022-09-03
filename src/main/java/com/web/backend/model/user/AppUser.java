package com.web.backend.model.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppUser {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private UserKind userKind;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoggedAt;
    private List<String> permissions;
}
