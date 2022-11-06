package com.web.backend;

import com.github.javafaker.Faker;
import com.web.backend.exception.AlreadyExistsException;
import com.web.backend.model.crewAssignment.Employee;
import com.web.backend.model.crewAssignment.EmployeeType;
import com.web.backend.model.crewAssignment.Zone;
import com.web.backend.model.job.Job;
import com.web.backend.model.job.Review;
import com.web.backend.model.user.AppUser;
import com.web.backend.model.user.Client;
import com.web.backend.model.user.UserKind;
import com.web.backend.repositories.crewAssignment.EmployeeRepository;
import com.web.backend.repositories.crewAssignment.ZoneRepository;
import com.web.backend.repositories.scheduleManagement.JobRepository;
import com.web.backend.repositories.scheduleManagement.ScheduleRepository;
import com.web.backend.repositories.userManagement.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootApplication @AllArgsConstructor @Slf4j
public class BackendApplication {
	private final MongoTemplate template;
	private final JobRepository jobRepository;
	private final ScheduleRepository scheduleRepository;
	private final UserRepository userRepository;
	private final ZoneRepository zoneRepository;
	private final EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner CommandLineRunnerBean() {
//		return (args) -> {
//			log.info("Cleansing DB in preparation for demo data...");
//			jobRepository.deleteAll();
//			scheduleRepository.deleteAll();
//			employeeRepository.deleteAll();
//			zoneRepository.deleteAll();
//
//			Faker faker = new Faker();
//
//			for (int i = 0; i < 10; i++) {
//				log.info("Insert new zone... {}", i);
//				Zone zone = new Zone();
//				zone.setSign(faker.bothify("?????").toUpperCase());
//				zone.setName(faker.pokemon().name());
//				zone.setDescription(faker.pokemon().location());
//				zone.setCreatedOn(LocalDate.now());
//				zone.setLastUpdatedOn(LocalDate.now());
//				zone.setDisabled(faker.bool().bool());
//
//				zoneRepository.save(zone);
//			}
//
////			for (int i = 0; i < 5; i++) {
////				log.info("Inserting new user... {}", i);
////				AppUser appUser = new AppUser();
////				appUser.setEmail(faker.bothify("????####@gmail.com"));
////				appUser.setPassword("testuser");
////				appUser.setUserKind(new Client());
////				appUser.setCreatedAt(LocalDateTime.now());
////				appUser.setLastLoggedAt(LocalDateTime.now());
////				appUser.setPermissions(List.of("USER"));
////				appUser.setAuthorized(true);
////				appUser.setAuthorizationToken(UUID.randomUUID().toString());
////				appUser.setResetToken(UUID.randomUUID().toString());
////
////				userRepository.save(appUser);
////			}
//
//			for (int i = 0; i < 50; i++) {
//				log.info("Inserting new employee... {}", i);
//				Employee employee = new Employee();
//				employee.setFirstName(faker.name().firstName());
//				employee.setLastName(faker.name().lastName());
//				if (faker.bool().bool()) {
//					employee.setJobTitle(EmployeeType.CLEANER);
//				} else {
//					employee.setJobTitle(EmployeeType.DRIVER);
//				}
//				employee.setJoinedOn(LocalDate.now());
//				employee.setLastJobOn(LocalDate.now());
//
//				//Randomly find distinct zones and attach to employee
//				var zoneAssignmentList = new ArrayList<Employee.ZoneAssignment>();
//				Aggregation agg = Aggregation.newAggregation(Aggregation.sample(faker.number().numberBetween(1,4)));
//				List<Zone> zones = template.aggregate(agg, "zone", Zone.class)
//						.getMappedResults().stream().distinct().toList();
//				zones.forEach(zone -> {
//					var newAssignment = new Employee.ZoneAssignment(
//							zone.getId(), zone.getSign(), LocalDate.now(), LocalDate.now());
//					zoneAssignmentList.add(newAssignment);
//				});
//				employee.setZoneAssignmentsList(zoneAssignmentList);
//
//				employeeRepository.save(employee);
//			}
//
//			for (int i = 0; i < 500; i++) {
//				log.info("Inserting new job... {}", i);
//				Job job = new Job();
//				job.setAddress(faker.address().fullAddress());
//				job.setPaymentId(faker.idNumber().valid());
//				job.setInvoiceId(faker.idNumber().valid());
//				job.setAmount(faker.number().numberBetween(1000, 10000));
//				if (faker.bool().bool()) {
//					job.setDate(faker.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//					job.setStartTime(LocalDateTime.of(job.getDate(), faker.date().future(10, TimeUnit.HOURS).toInstant().atZone(ZoneId.systemDefault()).toLocalTime()));
//					job.setEndTime(LocalDateTime.of(job.getDate(), faker.date().future(15, TimeUnit.HOURS).toInstant().atZone(ZoneId.systemDefault()).toLocalTime()));
//					job.setCreatedAt(faker.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
//				} else {
//					job.setDate(faker.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//					job.setStartTime(LocalDateTime.of(job.getDate(), faker.date().past(10, TimeUnit.HOURS).toInstant().atZone(ZoneId.systemDefault()).toLocalTime()));
//					job.setEndTime(LocalDateTime.of(job.getDate(), faker.date().past(15, TimeUnit.HOURS).toInstant().atZone(ZoneId.systemDefault()).toLocalTime()));
//					job.setCreatedAt(faker.date().past(500, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
//				}
//
//				if (faker.bool().bool()) {
//					job.setReview(new Review(
//							faker.superhero().name(), faker.shakespeare().asYouLikeItQuote(), (float) (faker.number().randomDouble(2, 0, 5))
//					));
//				}
//				ArrayList<Job.JobServiceSimple> serviceList = new ArrayList<>();
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
//				//Randomly find and attach crew
//				ArrayList<Job.JobCrewMemberSimple> crewList = new ArrayList<>();
//				Aggregation agg = Aggregation.newAggregation(Aggregation.sample(faker.number().numberBetween(1,4)));
//				List<Employee> employees = template.aggregate(agg, "employee", Employee.class)
//						.getMappedResults().stream().distinct().toList();
//				employees.forEach(employee -> {
//					var crew = new Job.JobCrewMemberSimple(employee);
//					crewList.add(crew);
//				});
//				job.setCrewList(crewList);
//
//				//Randomly find and attach client
//				agg = Aggregation.newAggregation(Aggregation.sample(1));
//				AppUser user = template.aggregate(agg, "user", AppUser.class)
//						.getMappedResults().stream().findFirst().get();
//				job.setClient(new Job.JobClientSimple(
//						user.getId(),
//						user.getFirstName(),
//						user.getLastName(),
//						user.getEmail(),
//						user.getMobile()
//				));
//
//				//Randomly find and attach zone
//				agg = Aggregation.newAggregation(Aggregation.sample(1));
//				Zone zone = template.aggregate(agg, "zone", Zone.class)
//						.getMappedResults().stream().findFirst().get();
//				job.setZoneId(zone.getId());
//				job.setZoneSign(zone.getSign());
//
//				jobRepository.save(job);
//			}
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
