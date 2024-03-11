package com.youcode.tournoi.entities;

import com.youcode.tournoi.enums.TypeTournoi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tournois")
public class Tournoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameTournoi;
    @Enumerated(EnumType.STRING)
    private TypeTournoi type;
    @Column
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @OneToMany(mappedBy = "tournoi", cascade = CascadeType.ALL)
    private List<Match> matches;

    @OneToMany(mappedBy = "tournoi", cascade = CascadeType.ALL)
    private List<EquipeWithPlayer> equipeWithPlayers;

    @ManyToMany
    @JoinTable(
            name = "players_fifa",
            joinColumns = @JoinColumn(name = "tournoi_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;

    @ManyToMany
    @JoinTable(
            name = "equipes_tournois",
            joinColumns = @JoinColumn(name = "tournoi_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id")
    )
    private List<Equipe> equipes;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

}

