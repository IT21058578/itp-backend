package com.web.backend.services.userManagement;

import com.web.backend.exception.AlreadyExistsException;
import com.web.backend.exception.NotFoundException;
import com.web.backend.model.user.AppUser;
import com.web.backend.model.user.Client;
import com.web.backend.repositories.userManagement.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service @AllArgsConstructor @Slf4j
public class AuthorizationService {
    private final UserRepository repository;
    private final EmailService emailService;

    public void registerUser(AppUser appUser) {
        log.info("Checking if email already exists...");
        Optional<AppUser> existingUser = repository.findByEmail(appUser.getEmail());
        if (existingUser.isPresent()) { throw new AlreadyExistsException("This user already exists!");}

        log.info("Configuring user details...");
        appUser.setUserKind(new Client());
        appUser.setCreatedAt(LocalDateTime.now());
        appUser.setLastLoggedAt(LocalDateTime.now());
        appUser.setPermissions(List.of("USER"));
        appUser.setAuthorized(false);
        appUser.setAuthorizationToken(UUID.randomUUID().toString());
        appUser.setResetToken(UUID.randomUUID().toString());

        log.info("Saving newly registered user...");
        repository.save(appUser);

        log.info("Sending authorization email...");
        emailService.sendAuthorizationEmail(appUser);
    }

    public AppUser loginUser(String email, String password) {
        log.info("Checking if user exists...");
        AppUser existingUser = repository.findByEmail(email).orElseThrow(NotFoundException::new);

        log.info("Check if user is authorized...");
        if (!existingUser.isAuthorized()) {
            emailService.resendAuthorizationEmail(existingUser);
            throw new IllegalStateException("User is not authorized yet!");
        }

        log.info("Check if password is correct...");
        if(!existingUser.getPassword().equals(password)) {
            log.warn("Wrong password given. Real pass is : {}", existingUser.getPassword());
            throw new AlreadyExistsException("Password is incorrect!");
        }

        log.info("Configuring user details...");
        existingUser.setLastLoggedAt(LocalDateTime.now());
        repository.save(existingUser);

        log.info("Returning user...");
        return existingUser;
    }

    public void authorizeUser(String email, String authorizationToken) {
        log.info("Checking if user exists...");
        AppUser existingUser = repository.findByEmail(email).orElseThrow(NotFoundException::new);

        log.info("Checking if already authorized...");
        if (existingUser.isAuthorized()) {
            throw new IllegalStateException("User is already authorized!");
        } else {
            log.info("Configuring user details...");
            existingUser.setAuthorized(true);
        }

        log.info("Saving user details...");
        repository.save(existingUser);
    }

    public void sendResetPasswordMail(String email) {
        log.info("Checking if user exists...");
        AppUser existingUser = repository.findByEmail(email).orElseThrow(NotFoundException::new);

        log.info("Configuring user details...");
        existingUser.setResetToken(UUID.randomUUID().toString());

        log.info("Saving newly configured user...");
        repository.save(existingUser);

        log.info("Sending password reset email...");
        emailService.sendResetPasswordEmail(existingUser);
    }


    public void resetUserPassword(String email, String password, String resetToken) {
        log.info("Checking if user exists...");
        AppUser existingUser = repository.findByEmail(email).orElseThrow(NotFoundException::new);

        log.info("Matching reset token...");
        if (!existingUser.getResetToken().equals(resetToken)) {
            throw new ServerWebInputException("Reset token does not match!");
        } else {
            log.info("Configuring user details...");
            existingUser.setPassword(password);
            existingUser.setResetToken(UUID.randomUUID().toString());
        }

        log.info("Saving newly configured user...");
        repository.save(existingUser);
    }
}
