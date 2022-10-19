package com.web.backend.services;

import com.web.backend.exception.NotFoundException;
import com.web.backend.model.user.AppUser;
import com.web.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor @Slf4j
public class UserService {
    private final UserRepository repo;

    public AppUser getUser(String userId) {
        log.info("Getting details of user with id : {}", userId);
        return repo.findById(userId).orElseThrow(NotFoundException::new);
    }

    public void deleteUser(String userId) {
    }

    public void editUser(String userId, AppUser user) {
        log.info("Getting details of user with id : {}", userId);
        AppUser dbUser = repo.findById(userId).orElseThrow(NotFoundException::new);

        log.info("Verifying password...");
        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new IllegalStateException("Password does not match!");
        }

        //TODO: Create editing logic.
        log.info("Saving edited user...");
        repo.save(dbUser);
    }
}
