package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Gool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ButRepository extends JpaRepository<Gool, Long> {
    @Query("SELECT COUNT(b) FROM Gool b WHERE b.player.idUser = :playerId")
    int countByPlayerId(@Param("playerId") Long playerId);

}
