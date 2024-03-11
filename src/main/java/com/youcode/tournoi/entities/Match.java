package com.youcode.tournoi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatch;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateMatch;

    @Column
    @Temporal(TemporalType.TIME)
    private LocalTime heureMatch;

    @Column(name = "score_equipe1")
    private int scoreEquipe1;

    @Column(name = "score_equipe2")
    private int scoreEquipe2;

    @ManyToOne
    @JoinColumn(name = "tournoi_id")
    private Tournoi tournoi;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<Like> likes;

    @ManyToOne
    @JoinColumn(name = "equipe1_id")
    private Equipe equipe1;

    @ManyToOne
    @JoinColumn(name = "equipe2_id")
    private Equipe equipe2;
}
