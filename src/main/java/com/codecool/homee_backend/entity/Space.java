package com.codecool.homee_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    private List<Device> devices;
}
