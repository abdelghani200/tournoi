package com.youcode.tournoi.dtos.commentaire;

import com.youcode.tournoi.entities.Match;
import com.youcode.tournoi.entities.User;
import lombok.Data;

@Data
public class CommentaireDtoRes {

    private Long idCommentaire;
    private User user;
    private Match match;
    private String contenu;
}
