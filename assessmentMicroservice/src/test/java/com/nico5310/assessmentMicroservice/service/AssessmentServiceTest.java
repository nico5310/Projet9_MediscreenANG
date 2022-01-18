package com.nico5310.assessmentMicroservice.service;

import com.nico5310.assessmentMicroservice.beans.PatientBean;
import com.nico5310.assessmentMicroservice.model.Assessments;
import com.nico5310.assessmentMicroservice.proxies.NoteMSProxy;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@DisplayName("Test AssessmentService ")
@ContextConfiguration(classes = {AssessmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AssessmentServiceTest {

    @Autowired
    private AssessmentServiceImpl assessmentServiceImpl;

    @MockBean
    private AgeCalculator ageCalculator;

    @MockBean
    private TriggersCalculator triggersCalculator;

    @MockBean
    private NoteMSProxy noteMSProxy;

    @MockBean
    private PatientMSProxy patientMSProxy;


    private int  countTriggersInNotes;
    private int  age;
    private char sex;

    @Test
    @DisplayName("Test getDiabetesLevelRisk Assessment.NONE with parameters: age<30 triggers=0")
    void getDiabetesLevelRisk_WithAgeMoreLess30_AndNoneTriggers_Test() {

        when(patientMSProxy.getById(any(Integer.class))).thenReturn(new PatientBean());
        when(triggersCalculator.calculateTriggersInNotes(any(Integer.class))).thenReturn(0);
        when(ageCalculator.ageCalculate(any(LocalDate.class))).thenReturn(20);

        assertEquals(Assessments.NONE, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
    }

    @Test
    @DisplayName("Test getDiabetesLevelRisk Assessment.BORDERLINE with parameters: age>30 triggers=2")
    void getDiabetesLevelRisk_WithAgeMoreThan30_AndTwoTriggers_Test() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(1986, 1, 1), 'M', "1 address", "100-200-4000");

        when(patientMSProxy.getById(any(Integer.class))).thenReturn(patient1);
        when(triggersCalculator.calculateTriggersInNotes(any(Integer.class))).thenReturn(2);
        when(ageCalculator.ageCalculate(any(LocalDate.class))).thenReturn(35);

        assertEquals(Assessments.BORDERLINE, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
    }

    @Test
    @DisplayName("Test getDiabetesLevelRisk Assessment.IN_DANGER with parameters: M, age<=30, triggers=3")
    void getDiabetesLevelRisk_WithGenreMale_WithAgeMoreLess30_AndThreeTriggers_Test() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2000, 1, 1), 'M', "1 address", "100-200-4000");

        when(patientMSProxy.getById(any(Integer.class))).thenReturn(patient1);
        when(triggersCalculator.calculateTriggersInNotes(any(Integer.class))).thenReturn(3);
        when(ageCalculator.ageCalculate(any(LocalDate.class))).thenReturn(21);

        assertEquals(Assessments.IN_DANGER, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
    }

    @Test
    @DisplayName("Test getDiabetesLevelRisk Assessment.IN_DANGER with parameters: F, age<=30 triggers=4")
    void getDiabetesLevelRisk_WithGenreFemale_WithAgeMoreLess30_AndFourTriggers_Test() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2000, 1, 1), 'F', "1 address", "100-200-4000");

        when(patientMSProxy.getById(any(Integer.class))).thenReturn(patient1);
        when(triggersCalculator.calculateTriggersInNotes(any(Integer.class))).thenReturn(4);
        when(ageCalculator.ageCalculate(any(LocalDate.class))).thenReturn(21);

        assertEquals(Assessments.IN_DANGER, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
    }

    @Test
    @DisplayName("Test getDiabetesLevelRisk Assessment.IN_DANGER with parameters: age >30, triggers=6")
    void getDiabetesLevelRisk_WithAgeMoreThan30_AndFourTriggers_Test() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(1986, 1, 1), 'F', "1 address", "100-200-4000");

        when(patientMSProxy.getById(any(Integer.class))).thenReturn(patient1);
        when(triggersCalculator.calculateTriggersInNotes(any(Integer.class))).thenReturn(6);
        when(ageCalculator.ageCalculate(any(LocalDate.class))).thenReturn(35);

        assertEquals(Assessments.IN_DANGER, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
    }

    @Test
    @DisplayName("Test getDiabetesLevelRisk Assessment.EARLY_ONSET with parameters: M, age <30, triggers=5")
    void getDiabetesLevelRisk_WithGenreMale_WithAgeMoreLess30_AndFiveTriggers_Test() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2000, 1, 1), 'M', "1 address", "100-200-4000");

        when(patientMSProxy.getById(any(Integer.class))).thenReturn(patient1);
        when(triggersCalculator.calculateTriggersInNotes(any(Integer.class))).thenReturn(5);
        when(ageCalculator.ageCalculate(any(LocalDate.class))).thenReturn(21);

        assertEquals(Assessments.EARLY_ONSET, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
    }

    @Test
    @DisplayName("Test getDiabetesLevelRisk Assessment.EARLY_ONSET with parameters: F, age <30, triggers=7")
    void getDiabetesLevelRisk_WithGenreFemale_WithAgeMoreLess30_AndSevenTriggers_Test() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2000, 1, 1), 'F', "1 address", "100-200-4000");

        when(patientMSProxy.getById(any(Integer.class))).thenReturn(patient1);
        when(triggersCalculator.calculateTriggersInNotes(any(Integer.class))).thenReturn(7);
        when(ageCalculator.ageCalculate(any(LocalDate.class))).thenReturn(21);

        assertEquals(Assessments.EARLY_ONSET, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
    }

    @Test
    @DisplayName("Test getDiabetesLevelRisk Assessment.EARLY_ONSET with parameters: age >30, triggers=8")
    void getDiabetesLevelRisk_WithAgeMoreThan30_AndEightTriggers_Test() {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(1986, 1, 1), 'M', "1 address", "100-200-4000");

        when(patientMSProxy.getById(any(Integer.class))).thenReturn(patient1);
        when(triggersCalculator.calculateTriggersInNotes(any(Integer.class))).thenReturn(8);
        when(ageCalculator.ageCalculate(any(LocalDate.class))).thenReturn(35);

        assertEquals(Assessments.EARLY_ONSET, this.assessmentServiceImpl.getDiabetesLevelRisk(1));
    }
}

