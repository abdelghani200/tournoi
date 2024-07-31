package com.youcode.tournoi.entities;

import com.youcode.tournoi.enums.TypeCarton;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "player_id")
    private Player player;

    @Enumerated(EnumType.STRING)
    private TypeCarton type;

}
