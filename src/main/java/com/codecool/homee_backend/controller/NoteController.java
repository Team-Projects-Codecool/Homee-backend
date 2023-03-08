package com.codecool.homee_backend.controller;


import com.codecool.homee_backend.controller.dto.note.NewNoteDto;
import com.codecool.homee_backend.controller.dto.note.NoteDto;
import com.codecool.homee_backend.controller.dto.note.UpdatedNoteDto;
import com.codecool.homee_backend.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notes")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/{noteId}")
    public NoteDto getNote(@PathVariable UUID noteId) {
        return noteService.getNote(noteId);
    }

    @GetMapping(params = "deviceId")
    public List<NoteDto> getNotesForDevice(@RequestParam UUID deviceId) {
        return noteService.getNotesForDevice(deviceId);
    }

    @PostMapping
    public NoteDto addNewNote(@RequestBody NewNoteDto newNote) {
        return noteService.addNewNote(newNote);
    }

    @PutMapping(params = {"noteId", "deviceId"})
    public void assignNoteToDevice(@RequestParam UUID noteId, @RequestParam UUID deviceId) {
        noteService.assignNoteToDevice(noteId, deviceId);
    }

    @PutMapping("/{noteId}")
    public NoteDto updateNote(@PathVariable UUID noteId, @RequestBody UpdatedNoteDto updatedNote) {
        if (Objects.equals(updatedNote.id().toString(), noteId.toString())) {
            return noteService.updateNote(updatedNote);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable UUID noteId) {
        noteService.deleteNote(noteId);
    }
}
