package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
