package com.youcode.tournoi.entities;

import com.youcode.tournoi.enums.TypePlayer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player extends User{

    @Enumerated(EnumType.STRING)
    private TypePlayer type;

    @ManyToMany(mappedBy = "players")
    private List<Tournoi> tournois;

    @ManyToOne
    private Equipe equipe;

}
