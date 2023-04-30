package com.codecool.homee_backend.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    private UUID id = UUID.randomUUID();
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime creationTime = LocalDateTime.now();

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
