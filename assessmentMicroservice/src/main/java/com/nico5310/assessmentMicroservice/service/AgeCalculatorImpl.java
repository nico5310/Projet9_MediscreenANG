package com.nico5310.assessmentMicroservice.service;

import com.nico5310.assessmentMicroservice.beans.PatientBean;
import com.nico5310.assessmentMicroservice.proxies.PatientMSProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AgeCalculatorImpl implements AgeCalculator {

    private static final Logger logger = LoggerFactory.getLogger(AgeCalculatorImpl.class);

    @Autowired
    PatientMSProxy patientMSProxy;

    @Override
    public int ageCalculate(LocalDate birthDate) {

        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age <= 0) {
            logger.error("The age of patient is not valid");
            throw new IllegalArgumentException ("Person's birthdate isn't valid");
        }
        logger.info("The age of patient is:" + age);
        return age;

    }


    @Override
    public int getAge(Integer id) {

        PatientBean patient = patientMSProxy.getById(id);
        LocalDate birthDate = patient.getDob();
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age <= 0) {
            logger.error("The age of patient is not valid");
            throw new IllegalArgumentException ("Person's birthdate isn't valid");
        }
        logger.info("The age of patient is:" + age);
        return age;

    }
}
