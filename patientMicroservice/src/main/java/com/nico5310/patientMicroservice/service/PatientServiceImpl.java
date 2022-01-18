package com.nico5310.patientMicroservice.service;

import com.nico5310.patientMicroservice.model.Patient;
import com.nico5310.patientMicroservice.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;


    ///////    GET METHOD

    /**
     *
     * @inheritDoc
     */
    @Override
    public List<Patient> listPatient() {
        logger.info("Get all patients from Service");
        return patientRepository.findAll();
    }

    /**
     *
     * @inheritDoc
     */
    @Override
    public Patient getById(int id) {
        logger.info("Get patient by id from Service " + id);
        return patientRepository.getById(id);
    }

    ////////     ADD METHOD
    /**
     *
     * @inheritDoc
     */
    @Override
    public Patient addPatient(Patient patient) {

        logger.info("SUCCESS, add new patient" + patient + " is complete to DB from service");
        if (patient.getAddress().isEmpty()) {
            logger.info("address is empty");
            patient.setAddress("No specified");
        }
        if (patient.getPhone().isEmpty()) {
            logger.info("phone is empty");
            patient.setPhone("No specified");
        }
        return patientRepository.save(patient);
    }

    ///////  UPDATE METHOD
    /**
     *
     * @inheritDoc
     */
    @Override
    public Patient showUpdateForm(Integer id, Model model) {

        logger.info("Show Update form page is charged from Service");
        Patient patient = patientRepository.getById(id);
        model.addAttribute("patient", patient);
        return patient;
    }

    /**
     *
     * @inheritDoc
     */
    @Override
    public Patient updatePatient(Integer id, Patient patient) {

        logger.info("SUCCESS, Update "+ patient + " is complete from service");
        return patientRepository.save(patient);
    }

    //////// DELETE METHOD
    /**
     *
     * @inheritDoc
     */
    @Override
    public void deletePatient(Integer id) {

        Patient patient = patientRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        logger.info("SUCCESS, patient and all her notes are correctly delete to DB from controller");
        patientRepository.delete(patient);
    }

}
