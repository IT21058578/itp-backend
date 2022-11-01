package com.web.backend.controllers.userManagement;

import com.web.backend.model.user.AppUser;
import com.web.backend.services.userManagement.AuthorizationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth")
@AllArgsConstructor @Slf4j
public class AuthorizationController {
    private final AuthorizationService service;

    @PostMapping(value = "/register",params = {}) @CrossOrigin
    ResponseEntity<?> registerUser (@RequestBody AppUser appUser) {
        log.info("AuthorizationController received request to register new user...");
        service.registerUser(appUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/login",params = {"email", "password"}) @CrossOrigin
    ResponseEntity<?> loginUser (@RequestParam String email, @RequestParam String password) {
        log.info("AuthorizationController received request to login user...");
        return ResponseEntity.ok(service.loginUser(email, password));
    }

    @PostMapping(value = "/authorize",params = {"email", "authorizationToken"}) @CrossOrigin
    ResponseEntity<?> authorizeUser (@RequestParam String email, @RequestParam String authorizationToken) {
        log.info("AuthorizationController received request to authorize user...");
        service.authorizeUser(email, authorizationToken);
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/passwordreset", params = {}) @CrossOrigin
    ResponseEntity<?> sendResetPasswordMail (@RequestBody SendResetPasswordMailRequestBody body) {
        log.info("AuthorizationController received a request to send a reset password email with request body {}...", body);
        service.sendResetPasswordMail(body.email);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(value = "/passwordreset", params={"reset"}) @CrossOrigin
    ResponseEntity<?> resetUserPassword (@RequestBody ResetUserPasswordRequestBody body) {
        log.info("AuthorizationController received request to reset password with request body {} (no authentication)...", body);
        service.resetUserPassword(body.email, body.password, body.resetToken);
        return ResponseEntity.ok().build();
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    private static class SendResetPasswordMailRequestBody {
        private String email;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    private static class ResetUserPasswordRequestBody {
        private String email;
        private String password;
        private String resetToken;
    }
}

