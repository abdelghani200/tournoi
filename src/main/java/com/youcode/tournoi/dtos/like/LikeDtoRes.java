package com.youcode.tournoi.dtos.like;

import com.youcode.tournoi.entities.Match;
import com.youcode.tournoi.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class LikeDtoRes {
    private Long idLike;
    private User user;
    private Match match;
}
