package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.EquipeWithPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeWithPlayerRepository extends JpaRepository<EquipeWithPlayer, Long> {
}
