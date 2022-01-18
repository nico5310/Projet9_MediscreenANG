package com.nico5310.patientMicroservice.controller;

import com.nico5310.patientMicroservice.model.Patient;
import com.nico5310.patientMicroservice.service.PatientServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Test Controller patient")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientController.class)
public class PatientControllerTest {

    @Autowired
    private PatientController patientController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientServiceImpl patientServiceImpl;


    @Test
    @DisplayName(" Test get listPatient")
    public void getListPatientTest() throws Exception {

        Patient       patient1    = new Patient(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");
        Patient       patient2    = new Patient(2, "Doe2", "John2", LocalDate.of(2010, 10, 10), 'M', "2 address", "200-200-4000");
        Patient       patient3    = new Patient(3, "Doe3", "John3", LocalDate.of(2020, 10, 10), 'M', "3 address", "300-200-4000");
        List<Patient> listPatient = new ArrayList<Patient>();
        listPatient.add(patient1);
        listPatient.add(patient2);
        listPatient.add(patient3);

        mockMvc.perform(get("/patient/list")).andExpect(status().isOk());

        verify(patientServiceImpl, times(1)).listPatient();
    }


    @Test
    @DisplayName(" Test getById patient")
    public void getByIdTest() throws Exception {

      mockMvc.perform(get("/patient/getById?id=1")).andExpect(status().isOk());
    }


    @Test
    @DisplayName(" Test get addPatient")
    public void getAddPatientTest() throws Exception {

        mockMvc.perform(post("/patient/add")
                .content("{ \"family\":\"Doe1\", \"given\":\"John1\", \"dob\":\"2020-10-10\", \"sex\":\"M\", \"address\":\"1 address\", \"phone\":\"100-222-1000\" }")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @DisplayName(" Test updateFormPatient")
    public void updateFormPatientTest() throws Exception {

        Patient       patient1    = new Patient(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");

        mockMvc.perform(get("/patient/showUpdateForm/1")).andExpect(status().isOk());
    }



    @Test
    @DisplayName(" Test updatePatient")
    public void updatePatientTest() throws Exception {

        Patient       patient1    = new Patient(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");
        when(patientServiceImpl.updatePatient(any(Integer.class),any(Patient.class))).thenReturn(patient1);

        mockMvc.perform(post("/patient/update/1")
                .content("{ \"firstName\":\"Doe1\", \"lastName\":\"John1\", \"dob\":\"2020-10-10\", \"genre\":\"F\", \"address\":\"1 address\", \"phone\":\"100-222-1000\" }")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @DisplayName(" Test delete patient")
    public void deletePatientTest() throws Exception {

        mockMvc.perform(get("/patient/delete/1")).andExpect(status().isOk());
    }



}
