package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.commentaire.CommentaireDtoRes;
import com.youcode.tournoi.dtos.commentaire.CommentaireReq;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.security.AuthService;
import com.youcode.tournoi.services.interfaces.CommentaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    private final CommentaireService commentaireService;
    private final AuthService authService;


    public CommentaireController(CommentaireService commentaireService, AuthService authService) {
        this.commentaireService = commentaireService;
        this.authService = authService;
    }

    @GetMapping
    public List<CommentaireDtoRes> getAllCommentaires(){
        return commentaireService.getAll();
    }

    @PutMapping("/update/{commentaireId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<CommentaireDtoRes> updateCommentaire(@PathVariable Long commentaireId) {
        CommentaireDtoRes updatedCommentaire = commentaireService.validerCommentaire(commentaireId);
        return ResponseEntity.ok(updatedCommentaire);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<CommentaireReq> addCommentaire(@RequestBody CommentaireReq commentaireReq) {
        Player player = authService.getAuthenticatedPlayer();
        CommentaireReq createdCommentaire = commentaireService.addCommentaire(commentaireReq, player);
        return new ResponseEntity<>(createdCommentaire, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteComment(@PathVariable Long id){
        commentaireService.delete(id);
    }


}
