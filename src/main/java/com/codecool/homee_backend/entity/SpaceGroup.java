package com.codecool.homee_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpaceGroup {
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private String about;
    @ManyToOne(fetch = FetchType.LAZY)
    private HomeeUser homeeUser;
    @OneToMany(mappedBy = "spaceGroup", cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    private List<Space> spaces = new ArrayList<>();

    public SpaceGroup(String name, String about) {
        this.name = name;
        this.about = about;
    }
}
