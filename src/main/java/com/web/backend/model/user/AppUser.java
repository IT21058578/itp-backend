package com.web.backend.model.user;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Data
@Document(collection = "user")
public class AppUser {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String address;

    @Indexed(unique = true)
    private String email;
    private String password;
    private String authorizationToken; //For authorizing email;
    private String resetToken; //For password reset;
    private boolean isAuthorized;
    private UserKind userKind;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoggedAt;
    private List<String> permissions;

    public AppUser(String firstName, String lastName, String address, String email, String password, String authorizationToken, String resetToken, boolean isAuthorized, UserKind userKind, LocalDate dateOfBirth, LocalDateTime createdAt, LocalDateTime lastLoggedAt, List<String> permissions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.authorizationToken = authorizationToken;
        this.resetToken = resetToken;
        this.isAuthorized = isAuthorized;
        this.userKind = userKind;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.lastLoggedAt = lastLoggedAt;
        this.permissions = permissions;
    }
}
