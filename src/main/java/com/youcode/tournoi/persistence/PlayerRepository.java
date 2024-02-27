package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p WHERE LOWER(p.nomUser) = LOWER(:searchTerm) OR CAST(p.idUser AS string) = :searchTerm OR LOWER(p.prenomUser) = LOWER(:searchTerm)")
    List<Player> searchPlayers(@Param("searchTerm") String searchTerm);
}
