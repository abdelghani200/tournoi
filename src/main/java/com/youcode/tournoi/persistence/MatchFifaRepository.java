package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.MatchFifa;
import com.youcode.tournoi.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchFifaRepository extends JpaRepository<MatchFifa, Long> {
    List<MatchFifa> findByTournoiId(Long tournoiId);

    List<MatchFifa> findByPlayer1OrPlayer2(Player player1, Player player2);
}
