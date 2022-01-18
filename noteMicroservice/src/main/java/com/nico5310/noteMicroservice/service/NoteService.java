package com.nico5310.noteMicroservice.service;


import com.nico5310.noteMicroservice.model.Note;
import org.springframework.ui.Model;

import java.util.List;

public interface NoteService  {

    /**
     * Return a list of all notes for a Patient
     * @param patientId patient id
     * @return a list of notes by patient id
     */
    List<Note> findByPatientId(Integer patientId);

    /**
     * Return a note by note id
     * @param id note id
     * @return a note by note id
     */
    Note getNoteById(String id);

    /**
     * Save e new note with note object
     * @param note note object
     * @return boolean
     */
    Note addNote(Note note);

    /**
     * Return model note with note id and patient id
     * @param id note id
     * @param patientId patient id
     * @param model note object model
     * @return model note  from note id and patient id
     */
    Note showUpdateNoteForm(String id, Integer patientId, Model model);

    /**
     * Return true of note exist and have been updated
     * @param id note id
     * @param note note object
     * @return true of note exist and have been updated
     */
    Note updateNote(String id, Note note);

    /**
     * Delete a note by her id
     * @param id note id
     */
    void deleteNoteById(String id);

    /**
     * Delete all notes for a patient by patient id
     * @param patientId patient id
     */
    void deleteAllNotesByPatientId(Integer patientId);
}
