//package com.youcode.tournoi.entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "equipes_fifa")
//public class EquipeFifa {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idEquipeFifa;
//
//    @ManyToOne
//    @JoinColumn(name = "tournoi_id")
//    private Tournoi tournoi;
//
//    @ManyToMany
//    @JoinTable(
//            name = "equipes_fifa_players",
//            joinColumns = @JoinColumn(name = "equipe_fifa_id"),
//            inverseJoinColumns = @JoinColumn(name = "player_id")
//    )
//    private List<Player> players;
//
//}
