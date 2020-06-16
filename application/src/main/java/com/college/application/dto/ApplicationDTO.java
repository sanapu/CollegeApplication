package com.college.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private double gpa;
    private double maxGpa;
    private String inState;
    private String fromState;
    private int satScore;
    private int actScore;
    private int felonies;
    // TODO: Create college Object to support for location/state check
    // private College college;
}
