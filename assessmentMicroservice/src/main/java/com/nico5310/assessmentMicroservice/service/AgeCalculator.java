package com.nico5310.assessmentMicroservice.service;

import java.time.LocalDate;

public interface AgeCalculator {


    int ageCalculate(LocalDate birthDate);


    int getAge(Integer id);
}
