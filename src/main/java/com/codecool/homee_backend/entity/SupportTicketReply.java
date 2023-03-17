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
public class SupportTicketReply {
    @Id
    private final UUID id = UUID.randomUUID();
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private SupportTicket supportTicket;
    private LocalDateTime creationTime = LocalDateTime.now();

    public SupportTicketReply(String description) {
        this.description = description;
    }
}
