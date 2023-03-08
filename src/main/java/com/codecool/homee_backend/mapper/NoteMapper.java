package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.note.NewNoteDto;
import com.codecool.homee_backend.controller.dto.note.NoteDto;
import com.codecool.homee_backend.entity.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public NoteDto mapNoteEntityToDto(Note entity) {
        return new NoteDto(
                entity.getId(),
                entity.getDescription()
        );
    }

    public Note mapNoteDtoToEntity(NewNoteDto dto) {
        return new Note(
                dto.description()
        );
    }

}
