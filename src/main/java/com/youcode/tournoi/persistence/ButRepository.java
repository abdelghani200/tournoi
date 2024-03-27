package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Gool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ButRepository extends JpaRepository<Gool, Long> {
    @Query("SELECT COUNT(b) FROM Gool b WHERE b.player.idUser = :playerId")
    int countByPlayerId(@Param("playerId") Long playerId);

    @Query("SELECT b.player.nomUser, SUM(b.numberOfGoal) FROM Gool b GROUP BY b.player.nomUser ORDER BY SUM(b.numberOfGoal) DESC")
    List<Object[]> findGoalsByPlayerOrdered();

}
