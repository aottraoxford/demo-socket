package com.socket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socket.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class UserRole extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userRoleIdSeq")
    @SequenceGenerator(name = "userRoleIdSeq", sequenceName = "user_role_id_seq", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;
}
