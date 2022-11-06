package com.web.backend.services.card;

import com.web.backend.exception.NotFoundException;
import com.web.backend.model.card.card;
import com.web.backend.repositories.cardRepository.cardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class cardService {
    private final MongoTemplate template;
    private final cardRepository repository;

    public List<card> getCardList() {
        log.info("Getting all schedule details... ");
        return repository.findAll();
    }

    public void postCard(card card) {
        log.info("Creating new schedule with details: {} ...", card);
        repository.save(card);
    }

    public void editCard(String id, card card) {
        log.info("Getting details of card with id {} ...", id);
        card oldCard = repository.findById(id).orElseThrow(NotFoundException::new);
        log.info("Replacing old details with new details...");
        card.setId(oldCard.getId());
        log.info("Saving new card details...");
        repository.save(card);
    }

    public void deleteCard(String id) {
        log.info("Deleting schedule with id {} ...", id);
        repository.deleteById(id);
    }

}
