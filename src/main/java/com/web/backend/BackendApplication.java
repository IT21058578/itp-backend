package com.web.backend;

import com.web.backend.repositories.scheduleManagement.JobRepository;
import com.web.backend.repositories.scheduleManagement.ScheduleRepository;
import com.web.backend.repositories.userManagement.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication @AllArgsConstructor @Slf4j
public class BackendApplication {
	private final JobRepository jobRepository;
	private final ScheduleRepository scheduleRepository;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	//@Bean
//	public CommandLineRunner CommandLineRunnerBean() {
//		return (args) -> {
//			log.info("Deleting all jobs");
//			jobRepository.deleteAll();
//			Faker faker = new Faker();
//			for (int i = 0; i < 25; i++) {
//				Job job = new Job();
//				job.setZone("A");
//				job.setAddress(faker.address().fullAddress());
//				job.setPaymentId(faker.idNumber().valid());
//				job.setInvoiceId(faker.idNumber().valid());
//				job.setAmount(faker.number().numberBetween(1000, 10000));
//				job.setDate(faker.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//				job.setStartTime(LocalDateTime.of(job.getDate(), faker.date().future(10, TimeUnit.HOURS).toInstant().atZone(ZoneId.systemDefault()).toLocalTime()));
//				job.setEndTime(LocalDateTime.of(job.getDate(), faker.date().future(15, TimeUnit.HOURS).toInstant().atZone(ZoneId.systemDefault()).toLocalTime()));
//				job.setCreatedAt(faker.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
//
//				List<Job.JobCrewMemberSimple> crewList = new ArrayList<>();
//				while (faker.bool().bool()) {
//					crewList.add(new Job.JobCrewMemberSimple(
//							faker.idNumber().valid(),
//							faker.name().firstName(),
//							faker.name().lastName()
//					));
//				}
//				job.setCrewList(crewList);
//
//				List<Job.JobServiceSimple> serviceList = new ArrayList<>();
//				while (faker.bool().bool()) {
//					serviceList.add(new Job.JobServiceSimple(
//							faker.idNumber().valid(),
//							faker.app().name(),
//							faker.number().numberBetween(100, 1000),
//							faker.number().numberBetween(1, 5)
//					));
//				}
//				job.setServiceList(serviceList);
//
//				job.setClient(new Job.JobClientSimple(
//						"bruh",
//						"Tharindu",
//						"Gunasekera",
//						"gunasekeratharindu@gmail.com",
//						"0773126991"
//				));
//
//				if (faker.bool().bool()) {
//					job.setReview(new Review(
//							faker.superhero().name(), faker.shakespeare().asYouLikeItQuote(), (float) (faker.number().randomDouble(2, 0, 5))
//					));
//				}
//
//				log.info("Saving a new demo job...");
//				jobRepository.save(job);
//			}
//
//		};
//	}

//	@Bean
//	public CommandLineRunner CommandLineRunnerBean() {
//			return (args) -> {
//				Faker faker = new Faker();
//				scheduleRepository.deleteAll();
//
//				for (int i = 0; i < 30; i++) {
//					String title = faker.book().title();
//					String description = faker.shakespeare().hamletQuote();
//					LocalDate date =
//							faker.date().future(100, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
//									.toLocalDate();
//					LocalTime startTime =
//							faker.date().future(100, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
//									.toLocalTime();
//					LocalTime endTime =
//							faker.date().future(100, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
//									.toLocalTime();
//					boolean isActive = faker.random().nextBoolean();
//
//					Schedule schedule = new Schedule(title, date, startTime, endTime, description, isActive);
//					scheduleRepository.save(schedule);
//				}
//			};
//		}

}
