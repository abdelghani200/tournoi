package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
}
