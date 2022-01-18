package com.nico5310.noteMicroservice.service;

import com.nico5310.noteMicroservice.model.Note;
import com.nico5310.noteMicroservice.repositories.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Autowired
    private NoteRepository noteRepository;

    ///////  GET METHOD
    /**
     *
     * @inheritDoc
     */
    @Override
    public List<Note> findByPatientId(Integer patientId) {

        logger.info("Get all notes of patient by id : " + patientId + " from service");
        return noteRepository.findAllNotesByPatientId(patientId);
    }

    /**
     *
     * @inheritDoc
     */
    @Override
    public Note getNoteById(String id) {

        logger.info("Get note by id :" + id + " from service");
        return noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No found note for this id :" + id));
    }

    ///////  ADD METHOD
    /**
     *
     * @inheritDoc
     */
    @Override
    public Note addNote(Note note) {

        logger.info("SUCCESS, add new note is complete to DB from service");
        return noteRepository.save(note);
    }

    ///////  UPDATE METHOD
    /**
     *
     * @inheritDoc
     */
    @Override
    public Note showUpdateNoteForm(String id,Integer patientId, Model model) {

        logger.info("Show Update form complete is charged from service");
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:" + id));
        model.addAttribute("note", note);
        return note;
    }
    /**
     *
     * @inheritDoc
     */
    @Override
    public Note updateNote(String id, Note note) {

        logger.info("SUCCESS, Updating note is complete from service");
        return noteRepository.save(note);
    }

    /////// DELETE METHOD
    /**
     *
     * @inheritDoc
     */
    @Override
    public void deleteNoteById(String id) {

        logger.info("SUCCESS, note is correctly delete to DB from service");
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:" + id));
        noteRepository.delete(note);
    }

    /**
     *
     * @inheritDoc
     */
    @Override
    public void deleteAllNotesByPatientId(Integer patientId) {

        logger.info("SUCCESS, all notes for patient " + patientId + " is correctly delete to DB from service");
        noteRepository.deleteAllNotesByPatientId(patientId);
    }

}
