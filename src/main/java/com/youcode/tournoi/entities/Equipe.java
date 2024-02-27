package com.youcode.tournoi.entities;

import com.youcode.tournoi.enums.TypeEquipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipes")
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private TypeEquipe type;

    @ManyToMany(mappedBy = "equipes")
    private List<Tournoi> tournois;

    @OneToMany(mappedBy = "equipe")
    private List<Player> players;
}
