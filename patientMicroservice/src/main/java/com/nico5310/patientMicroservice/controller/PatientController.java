package com.nico5310.patientMicroservice.controller;

import com.nico5310.patientMicroservice.model.Patient;
import com.nico5310.patientMicroservice.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    ////////  GET REQUEST   ////////////
    @ApiOperation(value = "Get all patients")
    @RequestMapping("/patient/list")
    public List<Patient> listPatient() {

        logger.info("Get list patients from Controller");
        return patientService.listPatient();
    }

    @ApiOperation(value = "Get patient by id")
    @GetMapping("/patient/getById")
    public Patient getById(@RequestParam Integer id) {

        logger.info("Get patient by Id from DB from Controller " + id);
        return patientService.getById(id);
    }

    ////////////  ADD REQUEST /////////////
    @ApiOperation(value = "Saving new patient")
    @PostMapping("/patient/add")
    public Patient addPatient(@RequestBody Patient patient) {

        logger.info("SUCCESS, add new patient" + patient + " is complete to DB from controller");
        return patientService.addPatient(patient);
    }

    ///////////    UPDATE REQUEST  ///////////////
    @ApiOperation(value = "Show Update patient form")
    @GetMapping("/patient/showUpdateForm/{id}")
    public void showUpdateForm(@PathVariable("id") Integer id,Model model) {

        logger.info("Show Update form page is charged from controller");
        Patient patient = patientService.getById(id);
        model.addAttribute("patient", patient);
    }

    @ApiOperation(value = "Update patient")
    @PostMapping("/patient/update/{id}")
    public Patient updatePatient(@PathVariable ("id") Integer id, @RequestBody @Valid Patient patient) {

        logger.info("SUCCESS, Update "+ patient+ " is complete from controller");
        return patientService.updatePatient(id, patient);
    }

    ///////   DELETE REQUEST
    @ApiOperation(value = "Delete patient")
    @GetMapping("/patient/delete/{id}")
    public void deletePatient(@PathVariable ("id") Integer id) {

        logger.info("SUCCESS, patient and all her notes are correctly delete to DB from controller");
        patientService.deletePatient(id);
    }

}
