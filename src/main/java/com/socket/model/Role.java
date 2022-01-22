package com.socket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socket.model.audit.UserDateAudit;
import com.socket.model.enums.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleIdSeq")
    @SequenceGenerator(name = "roleIdSeq", sequenceName = "role_id_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleType type;

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();
}

