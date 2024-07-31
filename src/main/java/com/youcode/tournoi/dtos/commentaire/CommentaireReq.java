package com.youcode.tournoi.dtos.commentaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentaireReq {
    private Long idCommentaire;
    private Long matchId;
    private Long userId;
    private String contenu;
    private LocalTime heure;
    private LocalDate date;
}
