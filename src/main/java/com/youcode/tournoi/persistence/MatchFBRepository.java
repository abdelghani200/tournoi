package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.entities.MatchFB;
import com.youcode.tournoi.entities.MatchFifa;
import com.youcode.tournoi.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchFBRepository extends JpaRepository<MatchFB, Long> {
    List<MatchFB> findByTournoiId(Long tournoiId);

    List<MatchFB> findByEquipe1OrEquipe2(Equipe equipe1, Equipe equipe2);
}
