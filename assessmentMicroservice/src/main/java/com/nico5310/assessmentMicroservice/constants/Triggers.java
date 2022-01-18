package com.nico5310.assessmentMicroservice.constants;

import java.util.List;

public class Triggers {

    /**
     * List of Triggers that can be used to calculate the risk of diabetes
     */
    protected static final List<String> triggersList = List.of("Hemoglobin A1C", "MicroAlbumin", "Height", "Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies");


    public static List<String> getTriggerList() {
        return triggersList;
    }

}
