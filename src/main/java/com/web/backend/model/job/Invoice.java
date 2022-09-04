package com.web.backend.model.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class Invoice {
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private double subTotal;
    private double discount;
    private double total;
    private double tax;
}
