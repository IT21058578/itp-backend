package com.web.backend.controllers;

import com.web.backend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user") @RestController @Slf4j
@AllArgsConstructor @CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping(params={"email"})
    public ResponseEntity<?> getUser(@RequestParam String email) {
        log.info("UserController received GET Request with email : {}", email);
        return ResponseEntity.ok(userService.getUser(email));
    }

    public ResponseEntity<?> changeUserRole(@RequestParam String email) {
        log.info("UserController received GET Request with email : {}", email);
        userService.changeUserRole(email);
    }
}
