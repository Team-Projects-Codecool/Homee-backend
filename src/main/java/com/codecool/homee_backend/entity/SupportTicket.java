package com.codecool.homee_backend.entity;


import com.codecool.homee_backend.entity.status.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupportTicket {
    @Id
    private final UUID id = UUID.randomUUID();
    @ManyToOne(fetch = FetchType.LAZY)
    private HomeeUser homeeUser;
    @OneToMany(mappedBy = "supportTicket", orphanRemoval = true)
    private List<SupportTicketReply> supportTicketReplies;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus = TicketStatus.OPEN;
    private LocalDateTime creationTime = LocalDateTime.now();
    private LocalDateTime closingTime;

    public SupportTicket(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
