package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.EquipeWithPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipeWithPlayerRepository extends JpaRepository<EquipeWithPlayer, Long> {
    List<EquipeWithPlayer> findByEquipeIdEquipe(Long equipeId);

    @Query("SELECT ep FROM EquipeWithPlayer ep WHERE ep.player.idUser = :playerId AND ep.tournoi.id = :tournoiId")
    List<EquipeWithPlayer> findByPlayerIdAndTournoiId(@Param("playerId") Long playerId, @Param("tournoiId") Long tournoiId);
}
