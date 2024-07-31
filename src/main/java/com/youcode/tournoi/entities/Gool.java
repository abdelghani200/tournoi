package com.youcode.tournoi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "buts")
public class Gool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBut;
    private Integer numberOfGoal;
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
