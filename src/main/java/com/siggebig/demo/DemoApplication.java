package com.siggebig.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudenRepository repository, MongoTemplate mongoTemplate) {
		return args -> {

			System.out.println("-------- DB STARTED --------");

			Address address = new Address(
					"Sweden",
					"47395",
					"Henån"
			);
			Student student = new Student(
					"Sigge",
					"Alfredsson",
					"saorust@hotmail.com",
					Gender.MALE,
					address,
					List.of("CS", "Math"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);


			// One way of doing it with MongoQuery ( new way to me, i've always done it with the repo )
//			Query query = new Query();
//			query.addCriteria(Criteria.where("email").is(student.getEmail()));
//
//			List<Student> students = mongoTemplate.find(query, Student.class);
//
//			if (students.size() > 1 ) {
//				throw new IllegalStateException("found many studs with email " + student.getEmail());
//			}
//
//			if (students.isEmpty()) {
//				System.out.println("Inserting student " + student);
//				repository.save(student);
//			} else {
//				System.out.println(student + " already exists");
//			}

			repository.findStudentByEmail(student.getEmail())
					.ifPresentOrElse(s -> {
						System.out.println(student + " already exists");
					}, () -> {
						System.out.println("Inserting student " + student);
						repository.save(student);
					});

		};
	}


}
