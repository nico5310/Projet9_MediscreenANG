package com.nico5310.noteMicroservice.service;

import com.nico5310.noteMicroservice.model.Note;
import com.nico5310.noteMicroservice.repositories.NoteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("Test NoteService ")
@ContextConfiguration(classes = {NoteServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class NoteServiceTest {

    @Autowired
    private NoteServiceImpl noteServiceImpl;

    @Autowired
    private NoteService noteService;

    @MockBean
    private NoteRepository noteRepository;

    @Test
    @DisplayName(" Test listPatient")
    public void getListPatientTest() throws Exception {
        //GIVEN
        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");
        Note note2 = new Note("2", 1, LocalDate.now(), "qsdfgh");
        Note note3 = new Note("2", 1, LocalDate.now(), "wxcvbn");
        List<Note> listNote = new ArrayList<Note>();
        listNote.add(note1);
        listNote.add(note2);
        listNote.add(note3);
        //WHEN
        when(noteRepository.findAllNotesByPatientId(1)).thenReturn(listNote);
        noteService.findByPatientId(1);
        //THEN
        verify(noteRepository, times(1)).findAllNotesByPatientId(1);
    }

    @Test
    @DisplayName(" Test getNoteByIdTest note")
    public void getNoteByIdTest() throws Exception {
        //GIVEN
        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");
        //WHEN
        when(noteRepository.findById(any(String.class))).thenReturn(Optional.of(note1));
        Note note2 = noteService.getNoteById("1");
        //THEN
        assertEquals(note1, note2);

    }

    @Test
    @DisplayName(" Test getNoteByIdTest note Error")
    public void getNoteByIdErrorTest() throws Exception {
        //GIVEN
        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");
        //WHEN
        when(noteRepository.findById(any(String.class))).thenReturn(Optional.empty());
        //THEN
        assertThrows(IllegalArgumentException.class, () -> noteService.getNoteById("2"));
    }

    @Test
    @DisplayName(" Test addNote")
    public void addNoteTest() throws Exception {
        //GIVEN
        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");
        //WHEN
        when(noteRepository.save(any(Note.class))).thenReturn(note1);
        noteService.addNote(note1);
        //THEN
        verify(noteRepository, times(1)).save(note1);
    }

    @Test
    @DisplayName(" Test showUpdateForm test")
    public void showUpdateFormTest() throws Exception {
        //GIVEN
        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");
        //WHEN
        when(noteRepository.findById("1")).thenReturn(Optional.of(note1));
        noteService.showUpdateNoteForm("1",1, new ConcurrentModel());
        //THEN
        verify(noteRepository).findById("1");
    }

    @Test
    @DisplayName(" Test showUpdateForm test Error")
    public void showUpdateFormErrorTest() throws Exception {
        //GIVEN
        Note  note1 = new Note("1", 1, LocalDate.now(), "azerty");
        Model model = null;
        //WHEN
        when(noteRepository.findById("1")).thenReturn(Optional.empty());

        //THEN
        assertThrows(IllegalArgumentException.class, () -> noteService.showUpdateNoteForm("1",1,model));
    }

    @Test
    @DisplayName(" Test updateNote")
    public void updateNoteTest() throws Exception {
        //GIVEN
        Note note1 = new Note();
        note1.setId("1");
        note1.setPatientId(1);
        note1.setDate(LocalDate.ofEpochDay(1L));
        note1.setRecommendation("test1");
        when(noteRepository.save(note1)).thenReturn(note1);
        //WHEN
        Note note2 = new Note();
        note2.setId("1");
        note2.setPatientId(1);
        note2.setDate(LocalDate.ofEpochDay(1L));
        note2.setRecommendation("test2");
        //THEN
        noteServiceImpl.updateNote("1", note2);
        verify(noteRepository).save(note2);
    }

    @Test
    @DisplayName(" Test updateNote Error")
    public void updateNoteErrorTest() throws Exception {
        //THEN
        //WHEN
        when(noteRepository.findById(anyString())).thenReturn(Optional.empty());
        //THEN
        assertThrows(IllegalArgumentException.class, () -> noteService.getNoteById("1"));
    }


    @Test
    @DisplayName(" Test delete note by Id")
    public void deleteNoteTest() throws Exception {
        //GIVEN
        Note note1 = new Note("1", 1, LocalDate.now(), "azerty");
        //WHEN
        when(noteRepository.save(note1)).thenReturn(note1);
        when(noteRepository.findById("1")).thenReturn(Optional.of(note1));
        noteServiceImpl.deleteNoteById("1");
        //THEN
        verify(noteRepository).delete(note1);
    }

    @Test
    @DisplayName(" Test delete note by Id Error")
    public void deleteNoteErrorTest() throws Exception {
        //GIVEN
        Note note1 = new Note("2", 1, LocalDate.now(), "azerty");
        //WHEN
        when(noteRepository.save(note1)).thenReturn(note1);
        when(noteRepository.findById("1")).thenReturn(Optional.empty());
        //THEN
        assertThrows(IllegalArgumentException.class, () -> noteService.deleteNoteById("2"));
    }

    @Test
    @DisplayName(" Test delete all Notes By Patient Id ")
    void deleteAllNotesByPatientIdTest() {
        //GIVEN
        doNothing().when(this.noteRepository).deleteAllNotesByPatientId((Integer) any());
        //WHEN
        noteServiceImpl.deleteAllNotesByPatientId(1);
        //THEN
        verify(noteRepository, times(1)).deleteAllNotesByPatientId(any(Integer.class));
    }

}
