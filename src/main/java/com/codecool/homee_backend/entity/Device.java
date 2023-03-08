package com.codecool.homee_backend.entity;

import com.codecool.homee_backend.entity.type.DeviceType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "space")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Device {
    @Id
    @EqualsAndHashCode.Include
    private UUID id  = UUID.randomUUID();
    @NotBlank(message = "Cannot be empty.")
    private String name;
    @NotBlank(message = "Cannot be empty.")
    private String model;
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    private String spot;
    private LocalDateTime warrantyStart;
    private LocalDateTime warrantyEnd;
    private LocalDateTime purchaseDate;
    private Double purchasePrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Space space;
    @OneToMany(mappedBy = "device", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Note> notes = new ArrayList<>();
    private String about;

    public Device(String name, String model, DeviceType deviceType, String spot, LocalDateTime warrantyStart, LocalDateTime warrantyEnd, LocalDateTime purchaseDate, Double purchasePrice, LocalDateTime createdAt, LocalDateTime updatedAt, String about) {
        this.name = name;
        this.model = model;
        this.deviceType = deviceType;
        this.spot = spot;
        this.warrantyStart = warrantyStart;
        this.warrantyEnd = warrantyEnd;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.about = about;
    }

    public void addNote(Note note) {
        notes.add(note);
    }
}
