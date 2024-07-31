package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.TeamMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipeWithPlayerRepository extends JpaRepository<TeamMembership, Long> {
    List<TeamMembership> findByEquipeIdEquipe(Long equipeId);

    @Query("SELECT tm FROM TeamMembership tm WHERE tm.player.idUser = :playerId AND tm.tournoi.id = :tournoiId")
    List<TeamMembership> findByPlayerIdAndTournoiId(@Param("playerId") Long playerId, @Param("tournoiId") Long tournoiId);

    boolean existsByEquipeIdEquipeAndPlayerIdUserAndTournoiId(Long equipeId, Long playerId, Long tournoiId);
}
