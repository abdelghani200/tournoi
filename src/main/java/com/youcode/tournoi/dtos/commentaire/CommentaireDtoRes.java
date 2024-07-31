package com.youcode.tournoi.dtos.commentaire;

import com.youcode.tournoi.dtos.match.MatchFbDto;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.dtos.user.AdminDto;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CommentaireDtoRes {

    private Long idCommentaire;
    private PlayerDto user;
    private MatchFbDto match;
    private String contenu;
    private LocalTime heure;
    private LocalDate date;
    private Boolean Valide;
}
