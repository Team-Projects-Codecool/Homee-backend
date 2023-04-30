package com.codecool.homee_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HomeeUser {

    @Id
    private UUID id  = UUID.randomUUID();
    @Version
    private Integer version;
    @NotBlank(message = "Cannot be empty.")
    private String username;
    @NotBlank(message = "Cannot be empty.")
    @EqualsAndHashCode.Include
    @Column(unique = true)
    private String email;
    private String password;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime registeredTime = LocalDateTime.now();
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime lastLoggedIn;
    private String firstName;
    private String lastName;
    private String role;
    private String about;
    @OneToMany(mappedBy = "homeeUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    private List<SpaceGroup> spaceGroups = new ArrayList<>();
    @OneToMany(mappedBy = "homeeUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Notification> notifications = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "homee_user_space",
            joinColumns = @JoinColumn(name = "homee_user_id"),
            inverseJoinColumns = @JoinColumn(name = "space_id")
    )
    private Set<Space> spaces = new HashSet<>();
    @OneToMany(mappedBy = "homeeUser", cascade = CascadeType.ALL)
    private List<SupportTicket> supportTickets = new ArrayList<>();

    public HomeeUser(String username, String email, String password,
                     String firstName, String lastName,
                     String about) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
    }

    public void addSpace(Space space) {
        spaces.add(space);
    }

    public void addGroup(SpaceGroup spaceGroup) {
        spaceGroups.add(spaceGroup);
    }

    public void addNotification(Notification notification) { notifications.add(notification); }

    public void clearAllUserSpaces() {
        spaces.clear();
    }

    public void clearAllUserGroups() {
        spaceGroups.clear();
    }
}
