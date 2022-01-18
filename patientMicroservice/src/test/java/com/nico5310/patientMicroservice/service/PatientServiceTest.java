package com.nico5310.patientMicroservice.service;

import com.nico5310.patientMicroservice.model.Patient;
import com.nico5310.patientMicroservice.repositories.PatientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ConcurrentModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("Test Patient Service ")
@ContextConfiguration(classes = {PatientServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PatientServiceTest {

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    @DisplayName(" Test list patient")
    void listPatientTest() {
        //GIVEN
        Patient       patient1    = new Patient(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");
        Patient       patient2    = new Patient(2, "Doe2", "John2", LocalDate.of(2010, 10, 10), 'M', "2 address", "200-200-4000");
        Patient       patient3    = new Patient(3, "Doe3", "John3", LocalDate.of(2020, 10, 10), 'M', "3 address", "300-200-4000");
        List<Patient> listPatient = new ArrayList<Patient>();
        listPatient.add(patient1);
        listPatient.add(patient2);
        listPatient.add(patient3);
        //WHEN
        when(patientRepository.findAll()).thenReturn(listPatient);
        patientService.listPatient();
        //THEN
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    @DisplayName(" Test getById patient")
    public void getByIdTest() throws Exception {
        //GIVEN
        Patient patient1 = new Patient(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");
        //WHEN
        when(patientRepository.getById(any(Integer.class))).thenReturn(patient1);
        Patient patient2 = patientService.getById(1);
        //THEN
        assertEquals(patient1, patient2);
    }

    @Test
    @DisplayName(" Test addPatient")
    public void addPatientTest() throws Exception {
        //GIVEN
        Patient patient1 = new Patient(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");
        //WHEN
        when(patientRepository.save(any(Patient.class))).thenReturn(patient1);
        patientService.addPatient(patient1);
        //THEN
        verify(patientRepository, times(1)).save(patient1);
    }

    @Test
    @DisplayName(" Test addPatient with address and phone not specified")
    public void addPatient_WithNoSpecifiedAddressAndPhoneTest() throws Exception {
        //GIVEN
        Patient patient1 = new Patient(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "", "");
        //WHEN
        when(patientRepository.save(any(Patient.class))).thenReturn(patient1);
        patientService.addPatient(patient1);
        //THEN
        verify(patientRepository, times(1)).save(patient1);
        assertEquals("No specified", patient1.getAddress());
        assertEquals("No specified", patient1.getPhone());
    }

    @Test
    @DisplayName(" Test showUpdateForm")
    public void showUpdateFormTest() throws Exception {
        //GIVEN
        Patient patient1 = new Patient(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");
        //WHEN
        when(patientRepository.getById(1)).thenReturn(patient1);
        patientService.showUpdateForm(1, new ConcurrentModel());
        //THEN
        verify(patientRepository).getById(1);
    }

    @Test
    @DisplayName("Test updatePatient")
    public void updatePatientTest() throws Exception {
        //GIVEN
        Patient patient1 = new Patient();
        patient1.setId(1);
        patient1.setFirstName("Doe1");
        patient1.setLastName("John");
        patient1.setDob(LocalDate.ofEpochDay(1L));
        patient1.setGenre('M');
        patient1.setAddress("1 address");
        patient1.setPhone("100-200-4000");
        when(patientRepository.save(patient1)).thenReturn(patient1);
        //WHEN
        Patient patient2 = new Patient();
        patient2.setId(1);
        patient2.setFirstName("Doe1");
        patient2.setLastName("John");
        patient2.setDob(LocalDate.ofEpochDay(1L));
        patient2.setGenre('F');
        patient2.setAddress("1 address");
        patient2.setPhone("100-200-4000");
        //THEN
        patientServiceImpl.updatePatient(1, patient2);
        verify(patientRepository).save(patient2);
    }



    @Test
    @DisplayName(" Test delete patient")
    public void deletePatientTest() throws Exception {
        //GIVEN
        Patient patient1 = new Patient(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");
        //WHEN
        when(patientRepository.save(patient1)).thenReturn(patient1);
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));
        patientServiceImpl.deletePatient(1);
        //THEN
        verify(patientRepository).delete(patient1);
    }

    @Test
    @DisplayName(" Test delete patient Error")
    public void deletePatientErrorTest() throws Exception {
        //GIVEN
        Patient patient1 = new Patient(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");
        //WHEN
        when(patientRepository.save(patient1)).thenReturn(patient1);
        when(patientRepository.findById(1)).thenReturn(Optional.empty());
        //THEN
        assertThrows(IllegalArgumentException.class, () -> patientService.deletePatient(1));
    }

}

