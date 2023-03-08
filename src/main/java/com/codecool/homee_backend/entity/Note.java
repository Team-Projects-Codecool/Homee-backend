package com.codecool.homee_backend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    private UUID id = UUID.randomUUID();
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;

    public Note(String description) {
        this.description = description;
    }

}
