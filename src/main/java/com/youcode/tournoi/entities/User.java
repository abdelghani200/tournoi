package com.youcode.tournoi.entities;

import com.youcode.tournoi.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idUser;
    protected String nomUser;
    protected String prenomUser;
    protected String email;
    protected String password;
    @Enumerated(EnumType.STRING)
    protected UserRole role;
}
