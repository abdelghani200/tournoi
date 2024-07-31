package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.commentaire.CommentaireDtoRes;
import com.youcode.tournoi.dtos.commentaire.CommentaireReq;
import com.youcode.tournoi.entities.Commentaire;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.entities.User;

import java.util.List;

public interface CommentaireService {
    List<CommentaireDtoRes> getAll();
    CommentaireReq addCommentaire(CommentaireReq commentaireReq, Player player);
    CommentaireDtoRes validerCommentaire(Long commentaireId);
    void delete(Long id);
}
