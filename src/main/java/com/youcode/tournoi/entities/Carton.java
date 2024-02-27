package com.youcode.tournoi.entities;

import com.youcode.tournoi.enums.TypeCarton;
import jakarta.persistence.*;

@Entity
@Table(name = "cartons")
public class Carton {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarton;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "joueur_id")
    private Player joueur;

    @Enumerated(EnumType.STRING)
    private TypeCarton type;

}
