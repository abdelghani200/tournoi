package com.youcode.tournoi.persistence;

import com.youcode.tournoi.dtos.match.MatchDtoRes;
import com.youcode.tournoi.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query("SELECT m.idMatch FROM Match m WHERE m.tournoi.id = :tournoiId")
    List<Long> findByTournoiId(Long tournoiId);
}
