package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Carton;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartonRepository extends JpaRepository<Carton, Long> {
}
