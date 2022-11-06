package com.web.backend.repositories.cardRepository;

import com.web.backend.model.card.card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//cardRepository
@Repository
public interface cardRepository extends MongoRepository<card, String> {
}