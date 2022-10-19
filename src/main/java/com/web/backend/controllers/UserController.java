package com.web.backend.controllers;

import com.web.backend.model.user.AppUser;
import com.web.backend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user") @RestController @Slf4j
@AllArgsConstructor @CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getUser(@RequestParam String userId) {
        log.info("UserController received GET Request with userId : {}", userId);
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam String userId) {
        log.info("UserController received DELETE Request with userId : {}", userId);
        userService.deleteUser(userId); //Only disabled them
        return ResponseEntity.ok().build();
    }

    @PutMapping
    //TODO: Turn AppUser into a user object with only shit that can be changed
    public ResponseEntity<?> editUser(@RequestParam String userId, @RequestBody AppUser user) {
        log.info("UserController received PUT Request with userId : {} , and Review : {}", userId, user);
        userService.editUser(userId, user);
        return ResponseEntity.ok().build();
    }
}
