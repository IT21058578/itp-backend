package com.web.backend.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "user")
public class AppUser {
    @Id
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
