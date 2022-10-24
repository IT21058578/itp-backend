package com.university.university.university.Collections;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection ="toBeAssigned")
public class ToBeAssigned {

    @Id
    private String bookingId;
    @JsonFormat(pattern="yyyy-MM-DD")
    private Date date;
    @JsonFormat(pattern="HH:mm")
    private Date time;
    private String zone;
    private String address;
    private String crewId;
    private String crewMembers;
    
}
