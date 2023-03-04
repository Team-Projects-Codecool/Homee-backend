package com.codecool.homee_backend.entity;

import com.codecool.homee_backend.entity.type.DeviceType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
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
    private String about;
}
