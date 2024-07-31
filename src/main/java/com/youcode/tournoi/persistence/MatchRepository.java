package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
