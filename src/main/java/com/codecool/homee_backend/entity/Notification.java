package com.codecool.homee_backend.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {
    @Id
    private UUID id  = UUID.randomUUID();
    @ManyToOne(fetch = FetchType.LAZY)
    private HomeeUser homeeUser;
    private String message;
    private LocalDateTime creationDate = LocalDateTime.now();
    private Boolean read = false;

    public Notification(String message) {
        this.message = message;
    }
}
