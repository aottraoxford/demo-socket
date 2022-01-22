package com.socket.model;

import com.socket.model.audit.UserDateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    @SequenceGenerator(name = "usersIdSeq", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    @NotNull(message = "username required")
    @NotEmpty(message = "username can't be empty")
    @NotBlank(message = "username can't be blank")
    @Column(unique = true)
    private String username;

    private String password;

    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();
}