package com.youcode.tournoi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
public class Admin extends User{
    private String adress;
    private String phone;
    @OneToMany(mappedBy = "admin")
    private List<Tournoi> tournois;
}
