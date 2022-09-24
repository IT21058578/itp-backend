package com.web.backend;

import com.github.javafaker.Faker;
import com.web.backend.dto.ClientSimple;
import com.web.backend.dto.EmployeeSimple;
import com.web.backend.model.job.*;
import com.web.backend.model.jobService.Service;
import com.web.backend.repositories.JobRepository;
import com.web.backend.repositories.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication @AllArgsConstructor @Slf4j
public class BackendApplication {
	private final JobRepository jobRepository;
	private final ScheduleRepository scheduleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
	return (args) -> {
		Faker faker = new Faker();

		for (int i = 0; i < 100; i++) {
			String title = faker.book().title();
			String description = faker.shakespeare().hamletQuote();
			LocalDate date = faker.date().future(100, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalTime startTime = faker.date().future(100, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
			LocalTime endTime = faker.date().future(100, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
			boolean isActive = faker.random().nextBoolean();

			Schedule schedule = new Schedule(title, date, startTime, endTime, description, isActive);
			scheduleRepository.save(schedule);
		}
	};}


//	public CommandLineRunner CommandLineRunnerBean() {
//		return (args) -> {
//			Faker faker = new Faker();
//
//			jobRepository.deleteAll();
//			for (int i = 0; i < 200; i++) {
//				String createdBy = faker.idNumber().valid();
//				String lastUpdatedBy = faker.idNumber().valid();
//				Review review = new Review(faker.shakespeare().hamletQuote(),
//						(float) faker.random().nextDouble());
//				Service service = new Service();
//				Invoice invoice = new Invoice(LocalDateTime.now(), LocalDateTime.now(),
//						faker.random().nextDouble(), faker.random().nextDouble(),
//						faker.random().nextDouble(), faker.random().nextDouble());
//				Payment payment = new Payment();
//				ClientSimple client = new ClientSimple();
//				LocalDateTime startTime = faker.date().future(100, TimeUnit.DAYS)
//						.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//				LocalDateTime endTime = faker.date().future(100, TimeUnit.DAYS)
//						.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//				LocalDateTime createdAt = LocalDateTime.now();
//				LocalDateTime lastUpdatedAt = LocalDateTime.now();
//				List<EmployeeSimple> crew = new ArrayList<>();
//
//				do {
//					EmployeeSimple employee = new EmployeeSimple(faker.idNumber().valid(),
//							faker.name().firstName(), faker.name().lastName(), faker.job().title());
//					crew.add(employee);
//				} while (faker.random().nextBoolean());
//
//				Job job = new Job(createdBy, lastUpdatedBy,
//						review, service, invoice, payment,
//						client, startTime, endTime, createdAt,
//						lastUpdatedAt, crew);
//
//				log.info("Creating and saving new job : {} ", job);
//				jobRepository.save(job);
//			}
//			for (int i = 0; i < 100; i++) {
//				String createdBy = faker.idNumber().valid();
//				String lastUpdatedBy = faker.idNumber().valid();
//				Review review = new Review(faker.shakespeare().hamletQuote(),
//						(float) faker.random().nextDouble());
//				Service service = new Service();
//				ClientSimple client = new ClientSimple();
//				LocalDateTime startTime = faker.date().future(100, TimeUnit.DAYS)
//						.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//				LocalDateTime endTime = faker.date().future(100, TimeUnit.DAYS)
//						.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//				LocalDateTime createdAt = LocalDateTime.now();
//				LocalDateTime lastUpdatedAt = LocalDateTime.now();
//				List<EmployeeSimple> crew = new ArrayList<>();
//
//				do {
//					EmployeeSimple employee = new EmployeeSimple(faker.idNumber().valid(),
//							faker.name().firstName(), faker.name().lastName(), faker.job().title());
//					crew.add(employee);
//				} while (faker.random().nextBoolean());
//
//				Job job = new Job(createdBy, lastUpdatedBy,
//						review, service, null, null,
//						client, startTime, endTime, createdAt,
//						lastUpdatedAt, crew);
//
//				log.info("Creating and saving new job with invoice and payment : {} ", job);
//				jobRepository.save(job);
//			}
//			for (int i = 0; i < 100; i++) {
//				String createdBy = faker.idNumber().valid();
//				String lastUpdatedBy = faker.idNumber().valid();
//				Service service = new Service();
//				ClientSimple client = new ClientSimple();
//				LocalDateTime startTime = faker.date().future(100, TimeUnit.DAYS)
//						.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//				LocalDateTime endTime = faker.date().future(100, TimeUnit.DAYS)
//						.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//				LocalDateTime createdAt = LocalDateTime.now();
//				LocalDateTime lastUpdatedAt = LocalDateTime.now();
//				List<EmployeeSimple> crew = new ArrayList<>();
//
//				do {
//					EmployeeSimple employee = new EmployeeSimple(faker.idNumber().valid(),
//							faker.name().firstName(), faker.name().lastName(), faker.job().title());
//					crew.add(employee);
//				} while (faker.random().nextBoolean());
//
//				Job job = new Job(createdBy, lastUpdatedBy,
//						null, service, null, null,
//						client, startTime, endTime, createdAt,
//						lastUpdatedAt, crew);
//
//				log.info("Creating and saving new job without invoice, review and payment : {} ", job);
//				jobRepository.save(job);
//			}
//		};
//	}
}
