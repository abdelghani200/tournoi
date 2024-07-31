package com.youcode.tournoi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fb_matches")
public class MatchFB extends Match{

    @ManyToOne
    @JoinColumn(name = "equipe1_id")
    private Equipe equipe1;

    @ManyToOne
    @JoinColumn(name = "equipe2_id")
    private Equipe equipe2;

}
