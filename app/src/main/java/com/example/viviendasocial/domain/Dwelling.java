package com.example.viviendasocial.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dwelling {

    private long id;
    private String street;
    private String city;
    private String type;
    private int room;
    private boolean available;
    private LocalDate buildDate;
    private List<Long> applicantsIds;
}
