package com.codecool.homee_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "spaces", fetch = FetchType.LAZY)
    private Set<HomeeUser> homeeUsers = new HashSet<>();
    private String about;
    @OneToMany
    @JoinColumn(name = "space_id")
    private List<Device> devices;


//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, about);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Space)) return false;
//        Space that = (Space) o;
//        return Objects.equals(id, that.id)
//                && Objects.equals(name, that.name)
//                && Objects.equals(about, that.about);
//    }
}
