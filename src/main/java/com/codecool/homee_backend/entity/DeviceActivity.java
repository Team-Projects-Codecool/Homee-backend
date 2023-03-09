package com.codecool.homee_backend.entity;

import com.codecool.homee_backend.entity.type.ActivityType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceActivity {
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    private Device device;
    private String description;
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    public DeviceActivity(Device device, String description, ActivityType activityType) {
        this.device = device;
        this.description = description;
        this.activityType = activityType;
        this.date = LocalDateTime.now();
    }

}
