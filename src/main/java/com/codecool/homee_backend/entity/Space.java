package com.codecool.homee_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Space {
    @Id
    @EqualsAndHashCode.Include
    private UUID id  = UUID.randomUUID();
    private String name;
    @ManyToMany(mappedBy = "spaces", fetch = FetchType.LAZY)
    private Set<HomeeUser> homeeUsers = new HashSet<>();
    private String about;
    @OneToMany(mappedBy = "space", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Device> devices = new ArrayList<>();

    public Space(String name, String about) {
        this.name = name;
        this.about = about;
    }

    public void addHomeeUser(HomeeUser homeeUser) {
        homeeUsers.add(homeeUser);
    }

    public void addDevice(Device device) {
        devices.add(device);
    }
}
