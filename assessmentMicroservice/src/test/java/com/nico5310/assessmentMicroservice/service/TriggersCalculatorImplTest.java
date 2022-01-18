package com.nico5310.assessmentMicroservice.service;

import com.nico5310.assessmentMicroservice.beans.NoteBean;
import com.nico5310.assessmentMicroservice.proxies.NoteMSProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Test TriggersCalculator ")
@ContextConfiguration(classes = {TriggersCalculatorImpl.class})
@ExtendWith(SpringExtension.class)
class TriggersCalculatorImplTest {

    @Autowired
    private TriggersCalculatorImpl triggersCalculatorImpl;

    @MockBean
    private NoteMSProxy noteMSProxy;

    @Test
    @DisplayName(" Test calculate Triggers in note")
    void testCalculateTriggersInNotesTest() {

        NoteBean note1 = new NoteBean("1", 1, LocalDate.now(), "Height");
        NoteBean note2 = new NoteBean("2", 1, LocalDate.now(), "Weight");
        List<NoteBean> listNote = new ArrayList<NoteBean>();
        listNote.add(note1);
        listNote.add(note2);

        when(noteMSProxy.listNote(any(Integer.class))).thenReturn(listNote);

        assertEquals(2,  triggersCalculatorImpl.calculateTriggersInNotes(1));

        verify(noteMSProxy, times(1)).listNote((any(Integer.class)));
    }
}

