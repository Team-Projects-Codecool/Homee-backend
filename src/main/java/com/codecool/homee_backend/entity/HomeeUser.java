package com.codecool.homee_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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
}
