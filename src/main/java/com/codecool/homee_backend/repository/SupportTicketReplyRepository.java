package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.SupportTicketReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupportTicketReplyRepository extends JpaRepository<SupportTicketReply, UUID> {

    List<SupportTicketReply> findAllBySupportTicketId(UUID id);

}
