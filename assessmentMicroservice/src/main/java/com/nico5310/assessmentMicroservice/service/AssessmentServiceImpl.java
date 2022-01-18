package com.nico5310.assessmentMicroservice.service;

import com.nico5310.assessmentMicroservice.beans.PatientBean;
import com.nico5310.assessmentMicroservice.model.Assessments;
import com.nico5310.assessmentMicroservice.proxies.NoteMSProxy;
import com.nico5310.assessmentMicroservice.proxies.PatientMSProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private static final Logger logger = LoggerFactory.getLogger(AssessmentServiceImpl.class);

    @Autowired
    private PatientMSProxy patientMSProxy;

    @Autowired
    private NoteMSProxy noteMSProxy;

    @Autowired
    private AgeCalculator ageCalculator;

    @Autowired
    private TriggersCalculator triggersCalculator;


    @Override
    public Assessments getDiabetesLevelRisk(Integer id) {

        logger.info("Get diabetes level risk");
        PatientBean patient = patientMSProxy.getById(id);
        int countTriggersInNotes = triggersCalculator.calculateTriggersInNotes(id);
        int age = ageCalculator.ageCalculate(patient.getDob());
        char sex = patient.getGenre();

       if ((sex =='M' || sex =='F') && age > 30 && countTriggersInNotes > 1 && countTriggersInNotes < 6) {
            logger.info("The diabetes risk is " + Assessments.BORDERLINE);
            return Assessments.BORDERLINE;

        } else if  (sex == 'M' && age <=30 && countTriggersInNotes >=3 && countTriggersInNotes < 5) {
            logger.info("The diabetes risk is " + Assessments.IN_DANGER);
            return Assessments.IN_DANGER;
        } else if  (sex == 'F' && age <=30 && countTriggersInNotes >=4 && countTriggersInNotes < 7) {
            logger.info("The diabetes risk is " + Assessments.IN_DANGER);
            return Assessments.IN_DANGER;
        } else if  ( age > 30 && countTriggersInNotes >= 6 && countTriggersInNotes <8) {
            logger.info("The diabetes risk is " + Assessments.IN_DANGER);
            return Assessments.IN_DANGER;


        } else if  (sex == 'M' && age <=30 && countTriggersInNotes >=5 && countTriggersInNotes < 8) {
            logger.info("The diabetes risk is " + Assessments.EARLY_ONSET);
            return Assessments.EARLY_ONSET;
        } else if  (sex == 'F' && age <=30 && countTriggersInNotes == 7) {
            logger.info("The diabetes risk is " + Assessments.EARLY_ONSET);
            return Assessments.EARLY_ONSET;
        } else if  ((sex == 'M' || sex =='F') && age > 30 && countTriggersInNotes >= 8) {
            logger.info("The diabetes risk is " + Assessments.EARLY_ONSET);
            return Assessments.EARLY_ONSET;

       } else {
           return Assessments.NONE;
       }

    }

}
