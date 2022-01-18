package com.nico5310.noteMicroservice.controller;

import com.nico5310.noteMicroservice.model.Note;
import com.nico5310.noteMicroservice.service.NoteService;
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
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    ///////////   GET REQUEST //////////////
    @ApiOperation(value = "Get all notes")
    @GetMapping("/note/list/{patientId}")
    public List<Note> listNote(@PathVariable ("patientId") Integer patientId) {

        logger.info("Get all notes of patient by id : " + patientId + " from controller");
        return noteService.findByPatientId(patientId);
    }

    @ApiOperation(value = "Get note by id")
    @GetMapping("/note/getNoteById")
    public Note getNoteById(@RequestParam String id) {

        logger.info("Get note by id :" + id + " from controller");
        return noteService.getNoteById(id);
    }


    //////////  ADD REQUEST   ///////////////
    @ApiOperation(value = "Saving new note")
    @PostMapping("/note/add")
    public Note addNote(@Valid @RequestBody Note note) {

        logger.info("SUCCESS, add new note is complete to DB from controller");
        return noteService.addNote(note);
    }

    /////////  UPDATE REQUEST  ///////////////

    @ApiOperation(value = "Show Update note form")
    @GetMapping("/note/showUpdateForm/{id}/{patientId}")
    public void showUpdateNoteForm(@PathVariable("id") String id,Integer patientId, Model model) {

        logger.info("Show Update form complete is charged from controller");
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
    }


    @ApiOperation(value = "Update note")
    @PostMapping("/note/update/{id}")
    public Note updateNote(@PathVariable ("id") String id, @Valid @RequestBody Note note) {

        logger.info("SUCCESS, Updating note is complete from controller");
        note.setId(id);
        return noteService.updateNote(id, note);
    }

    //////// DELETE REQUEST ///////////////
    @ApiOperation(value = "Delete note")
    @GetMapping("/note/delete/{id}")
    public void deleteNote(@PathVariable ("id") String id) {

        logger.info("SUCCESS, this note is correctly delete to DB from controller");
        noteService.deleteNoteById(id);
    }

    @ApiOperation(value = "Delete all notes by patient")
    @GetMapping("/note/delete/all/{patientId}")
    public void deleteAllNotesByPatientId(@PathVariable ("patientId") Integer patientId) {

        logger.info("SUCCESS, all notes for patient " + patientId + " is correctly delete to DB from controller");
        noteService.deleteAllNotesByPatientId(patientId);
    }
}
