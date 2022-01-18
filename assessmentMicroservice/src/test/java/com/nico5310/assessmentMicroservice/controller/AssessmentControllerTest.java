package com.nico5310.assessmentMicroservice.controller;

import com.nico5310.assessmentMicroservice.beans.PatientBean;
import com.nico5310.assessmentMicroservice.service.AgeCalculator;
import com.nico5310.assessmentMicroservice.service.AssessmentService;
import com.nico5310.assessmentMicroservice.service.TriggersCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AssessmentController.class)
class AssessmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AssessmentService assessmentService;

    @MockBean
    private AgeCalculator ageCalculator;

    @MockBean
    private TriggersCalculator triggersCalculator;


    @Test
    @DisplayName(" Test get level diabetes risk for a patient")
    public void getDiabetesLevelRiskTest() throws Exception {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");

        mockMvc.perform(get("/assessment/" + patient1.getId())).andExpect(status().isOk());

        verify(assessmentService, times(1)).getDiabetesLevelRisk(1);

    }

    @Test
    @DisplayName(" Test getAge of patient")
    public void getAgeTest() throws Exception {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");

        mockMvc.perform(get("/assessment/age/"+ patient1.getId())).andExpect(status().isOk());

        verify(ageCalculator, times(1)).getAge(1);

    }

    @Test
    @DisplayName(" Test getTriggers of patient")
    public void getTriggersTest() throws Exception {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");

        mockMvc.perform(get("/assessment/triggers/"+ patient1.getId())).andExpect(status().isOk());

        verify(triggersCalculator, times(1)).calculateTriggersInNotes(1);

    }

}

