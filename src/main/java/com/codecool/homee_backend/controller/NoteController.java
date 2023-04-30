package com.codecool.homee_backend.controller;


import com.codecool.homee_backend.controller.dto.note.NewNoteDto;
import com.codecool.homee_backend.controller.dto.note.NoteDto;
import com.codecool.homee_backend.controller.dto.note.UpdatedNoteDto;
import com.codecool.homee_backend.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

import static com.codecool.homee_backend.config.auth.SpringSecurityConfig.USER;

@RolesAllowed(USER)
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

    @PutMapping
    public NoteDto updateNote(@RequestBody UpdatedNoteDto updatedNote) {
        return noteService.updateNote(updatedNote);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable UUID noteId) {
        noteService.deleteNote(noteId);
    }
}
