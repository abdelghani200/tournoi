package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {

}
