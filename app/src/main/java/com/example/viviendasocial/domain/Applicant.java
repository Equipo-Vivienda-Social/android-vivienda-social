package com.example.viviendasocial.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Applicant {
    private long id;
    private String name;
    private String surname;
    private String dni;
    private LocalDate birthDate;
    private int salary;
    private int familyMembers;
    private boolean employed;
}
