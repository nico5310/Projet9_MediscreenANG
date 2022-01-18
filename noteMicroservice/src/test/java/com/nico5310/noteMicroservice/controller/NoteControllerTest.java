package com.nico5310.noteMicroservice.controller;


import com.nico5310.noteMicroservice.model.Note;
import com.nico5310.noteMicroservice.service.NoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Unit Test Controller note")
@ContextConfiguration(classes = {NoteController.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NoteController.class)
public class NoteControllerTest {


    @Autowired
    private NoteController noteController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    @DisplayName(" Test get list note for a patient")
    public void getListNoteByPatientTest() throws Exception {

        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");

        mockMvc.perform(get("/note/list/1")).andExpect(status().isOk());

        verify(noteService, times(1)).findByPatientId(1);
    }

    @Test
    @DisplayName(" Test getNoteById note")
    public void getNoteByIdTest() throws Exception {

        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");

        mockMvc.perform(get("/note/getNoteById/?id=1")).andExpect(status().isOk());

        verify(noteService, times(1)).getNoteById("1");
    }

    @Test
    @DisplayName(" Test get addNote")
    public void getAddNoteTest() throws Exception {

        Note note1 = new Note();

        when(noteService.addNote(any(Note.class))).thenReturn(note1);

        mockMvc.perform(post("/note/add").contentType(MediaType.APPLICATION_JSON)
                                         .sessionAttr("note", note1)
                                         .content("{ \"id\":\"1\", \"patientId\":\"1\", \"date\":\"2020-10-10\", \"recommendation\":\"test\"}"))
               .andExpect(status().isOk());

    }


    @Test
    @DisplayName(" Test get showUpdateForm note")
    public void showUpdateFormTest() throws Exception {

        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");

        when(noteService.getNoteById(any(String.class))).thenReturn(note1);

        mockMvc.perform(get("/note/showUpdateForm/{id}/{patientId}", 1, 1).sessionAttr("note", note1)
                                                                          .param("id", "1")
                                                                          .param("patientId", "1")
                                                                          .param("recommendation", "azerty"))
               .andExpect(status().isOk());
    }



    @Test
    @DisplayName(" Test updateNote note")
    public void updateFormTest() throws Exception {

        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");

        when(noteService.getNoteById(any(String.class))).thenReturn(note1);
        when(noteService.updateNote(any(String.class), any(Note.class))).thenReturn(note1);

        mockMvc.perform(post("/note/update/{id}", 1).contentType(MediaType.APPLICATION_JSON).sessionAttr("note", note1).content("{ \"id\":\"1\", \"patientId\":\"1\", \"date\":\"2020-10-10\", \"recommendation\":\"test\"}"))
               .andExpect(status().isOk());

    }

    @Test
    @DisplayName(" Test deleteNote")
    public void deleteNoteTest() throws Exception {

        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");

        mockMvc.perform(get("/note/delete/1")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(" Test deleteAllNotes")
    public void deleteAllNotesTest() throws Exception {

//        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");

        mockMvc.perform(get("/note/delete/all/1")).andExpect(status().isOk());
    }
}
