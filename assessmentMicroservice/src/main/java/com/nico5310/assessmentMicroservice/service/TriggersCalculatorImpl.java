package com.nico5310.assessmentMicroservice.service;

import com.nico5310.assessmentMicroservice.beans.NoteBean;
import com.nico5310.assessmentMicroservice.constants.Triggers;
import com.nico5310.assessmentMicroservice.proxies.NoteMSProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TriggersCalculatorImpl implements TriggersCalculator {

    private static final Logger logger = LoggerFactory.getLogger(TriggersCalculatorImpl.class);

    @Autowired
    NoteMSProxy noteMSProxy;

    @Override
    public int calculateTriggersInNotes(Integer id) {

        logger.info("Count trigger into Note of patient");
        List<NoteBean> notes          = noteMSProxy.listNote(id);
        List<String>   triggersList   = Triggers.getTriggerList();
        List<String>   triggersResult = new ArrayList<String>();
        int countTriggers = 0;
        for (NoteBean note : notes) {
            for (String trigger : triggersList) {
                if (note.getRecommendation().contains(trigger)) {
                    countTriggers++;
                }
            }
        }
        logger.info("Found  "+ countTriggers + " trigger to Note of patient");
        return countTriggers;
    }


}
