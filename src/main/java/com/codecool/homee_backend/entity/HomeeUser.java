package com.codecool.homee_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HomeeUser {

    @Id
    @EqualsAndHashCode.Include
    private UUID id  = UUID.randomUUID();
    @NotBlank(message = "Cannot be empty.")
    private String username;
    @NotBlank(message = "Cannot be empty.")
    private String email;
    @NotBlank(message = "Cannot be empty.")
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters.")
    private String password;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime registeredTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
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

    public HomeeUser(String username, String email, String password, LocalDateTime registeredTime, LocalDateTime lastLoggedIn, String firstName, String lastName, String about) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.registeredTime = registeredTime;
        this.lastLoggedIn = lastLoggedIn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
    }

    public void addSpace(Space space) {
        spaces.add(space);
    }
}
