package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournoiRepository extends JpaRepository<Tournoi, Long> {
}
