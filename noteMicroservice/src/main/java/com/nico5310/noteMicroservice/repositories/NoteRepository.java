package com.nico5310.noteMicroservice.repositories;

import com.nico5310.noteMicroservice.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findAllNotesByPatientId(Integer patientId);

    void deleteAllNotesByPatientId(Integer patientId);
}
