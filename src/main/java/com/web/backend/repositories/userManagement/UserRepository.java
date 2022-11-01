package com.web.backend.repositories.userManagement;

import com.web.backend.model.user.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {
    Optional<AppUser> findByEmail(String email);
    void deleteAppUserByEmail(String email);
}
