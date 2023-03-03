package com.codecool.homee_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "spaces")
public class HomeeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Cannot be empty.")
    private String username;
    @NotBlank(message = "Cannot be empty.")
    private String email;
    @NotBlank(message = "Cannot be empty.")
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters.")
    private String password;
    private LocalDateTime registeredTime;
    private LocalDateTime lastLoggedIn;
    private String firstName;
    private String lastName;
    private String about;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "homeeuser_space",
            joinColumns = @JoinColumn(name = "homeeuser_id"),
            inverseJoinColumns = @JoinColumn(name = "space_id")
    )
    private Set<Space> spaces = new HashSet<>();

    public void addSpace(Space space) {
        spaces.add(space);
        space.getHomeeUsers().add(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, registeredTime, lastLoggedIn, firstName, lastName, about);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomeeUser)) return false;
        HomeeUser that = (HomeeUser) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(registeredTime, that.registeredTime) && Objects.equals(lastLoggedIn, that.lastLoggedIn) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(about, that.about);
    }
}
