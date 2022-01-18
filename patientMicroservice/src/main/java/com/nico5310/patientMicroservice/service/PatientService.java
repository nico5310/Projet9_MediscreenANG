package com.nico5310.patientMicroservice.service;

import com.nico5310.patientMicroservice.model.Patient;
import org.springframework.ui.Model;

import java.util.List;

public interface PatientService {

    /**
     * Return a list of oll patients to database MySql
     * @return a List of patient
     */
    List<Patient> listPatient();

    /**
     * Return a patient by id parameter
     * @param id patient id
     * @return a patient by id parameter
     */
    Patient getById(int id);

    /**
     * Save a new patient with parameters model object
     * @param patient patient id
     * @return boolean
     */
    Patient addPatient(Patient patient);

    /**
     * Return a model patient by id for update form
     * @param id parameter id patient
     * @param model object model patient parameters
     * @return model from id patient
     */
    Patient showUpdateForm(Integer id, Model model);

    /**
     * Return true if patient exist and have been updated
     * @param id id by patient updated
     * @param patient Patient
     * @return boolean
     */
    Patient updatePatient(Integer id, Patient patient);

    /**
     * Delete patient by id parameter
     * @param id integer parameter patient
     */
    void deletePatient(Integer id);
}
