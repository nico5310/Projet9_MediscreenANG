package com.nico5310.noteMicroservice.service;

import com.nico5310.noteMicroservice.repositories.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {NoteServiceImpl.class})
@ExtendWith(SpringExtension.class)
class NoteServiceImplTest {

    @MockBean
    private NoteRepository noteRepository;

    @Autowired
    private NoteServiceImpl noteServiceImpl;

    @Test
    void testDeleteAllNotesByPatientId() {

        doNothing().when(this.noteRepository).deleteAllNotesByPatientId((Integer) any());
        this.noteServiceImpl.deleteAllNotesByPatientId(123);
        verify(this.noteRepository).deleteAllNotesByPatientId((Integer) any());
    }
}

