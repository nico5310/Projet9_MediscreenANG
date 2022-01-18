package com.nico5310.assessmentMicroservice.service;

import com.nico5310.assessmentMicroservice.beans.PatientBean;
import com.nico5310.assessmentMicroservice.proxies.PatientMSProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Test Age calculator")
@ContextConfiguration(classes = {AgeCalculatorImpl.class})
@ExtendWith(SpringExtension.class)
class AgeCalculatorTest {

    @Autowired
    private AgeCalculatorImpl ageCalculatorImpl;

    @MockBean
    private PatientMSProxy patientMSProxy;

    @Test
    @DisplayName(" Test age calculate with Valid parameter patient")
    void ageCalculateWithValidParamTest() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");

        assertEquals(21, ageCalculatorImpl.ageCalculate(patient1.getDob()));
    }


    @Test
    @DisplayName(" Test age calculate with invalid param patient")
    void ageCalculateWithInvalidParamTest() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2030, 10, 10), 'M', "1 address", "100-200-4000");

        assertThrows(IllegalArgumentException.class, () ->  ageCalculatorImpl.ageCalculate(patient1.getDob()) );
    }


    @Test
    @DisplayName(" Test age calculate patient with valid param")
    void getAgeWithValidParamTest() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");

        when(patientMSProxy.getById(any(Integer.class))).thenReturn(patient1);

        assertEquals(21, ageCalculatorImpl.getAge(patient1.getId()));
    }

    @Test
    @DisplayName(" Test age calculate patient with invalid param")
    void getAgeWithInvalidParamTest() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2030, 10, 10), 'M', "1 address", "100-200-4000");

        when(patientMSProxy.getById(any(Integer.class))).thenReturn(patient1);

        assertThrows(IllegalArgumentException.class, () ->  ageCalculatorImpl.getAge(patient1.getId()));
    }
}

