package com.web.backend.controllers.userManagement;

import com.web.backend.dto.userManagement.UserSearchSortParameters;
import com.web.backend.services.userManagement.UserService;
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

    @PostMapping(value="/search")
    public ResponseEntity<?> getUserList(@RequestBody UserSearchSortParameters searchParams) {
        log.info("UserController received POST request to search users");
        return ResponseEntity.ok(userService.getUserList(searchParams));
    }

    @PutMapping(params={"email", "role"}, value="/role")
    public ResponseEntity<?> changeUserRole(@RequestParam String email, @RequestParam String role) {
        log.info("UserController received GET Request with email : {}", email);
        userService.changeUserRole(email, role);
        return ResponseEntity.ok().build();
    }
}
