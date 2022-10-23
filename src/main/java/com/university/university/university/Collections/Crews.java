package com.university.university.university.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection ="crews")
public class Crews {
    @Id
	private String id;
	private String crewId;
	private String employeeId;
	private String employeeName;
    private String zone;
}
