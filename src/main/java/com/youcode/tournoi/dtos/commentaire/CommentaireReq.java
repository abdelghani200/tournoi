package com.youcode.tournoi.dtos.commentaire;

import lombok.Data;

@Data
public class CommentaireReq {
    private Long idCommentaire;
    private Long userId;
    private Long matchId;
    private String contenu;
}
