package com.web.backend.model.job;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
public class Job {
    private String id;
    private String zone;
    private String address;
    private String paymentId;
    private String invoiceId;
    private float amount;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate createdAt;
    private List<CrewMemberSimple> crewList;
    private List<ServiceSimple> serviceList;
    private ClientSimple client;
    private Review review;

    private class CrewMemberSimple {
        private String id;
        private String firstName;
        private String lastName;
    }

    private class ServiceSimple {
        private String id;
        private String name;
        private String cost;
        private String quantity;
    }

    private class ClientSimple {
        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String mobile;
    }
}
