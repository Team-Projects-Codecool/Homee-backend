package com.codecool.homee_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "space")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
