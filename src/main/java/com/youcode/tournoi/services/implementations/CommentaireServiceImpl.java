package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.commentaire.CommentaireReq;
import com.youcode.tournoi.entities.Commentaire;
import com.youcode.tournoi.persistence.CommentaireRepository;
import com.youcode.tournoi.services.interfaces.CommentaireService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentaireServiceImpl implements CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private final ModelMapper modelMapper;

    public CommentaireServiceImpl(CommentaireRepository commentaireRepository, ModelMapper modelMapper) {
        this.commentaireRepository = commentaireRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentaireReq save(CommentaireReq commentaireReq) {
        Commentaire commentaire = modelMapper.map(commentaireReq, Commentaire.class);
        Commentaire savedCommentaire = commentaireRepository.save(commentaire);
        return modelMapper.map(savedCommentaire, CommentaireReq.class);
    }
}
