package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.commentaire.CommentaireReq;
import com.youcode.tournoi.services.interfaces.CommentaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commentaire")
public class CommentaireController {

    private final CommentaireService commentaireService;

    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @PostMapping
    public ResponseEntity<CommentaireReq> createCommentaire(@RequestBody CommentaireReq commentaireReq){
        CommentaireReq createdCommentaire = commentaireService.save(commentaireReq);
        return  new ResponseEntity<>(createdCommentaire, HttpStatus.CREATED);
    }

}
