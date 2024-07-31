package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.commentaire.CommentaireDtoRes;
import com.youcode.tournoi.dtos.commentaire.CommentaireReq;
import com.youcode.tournoi.dtos.user.AdminDto;
import com.youcode.tournoi.dtos.user.AdminUDto;
import com.youcode.tournoi.dtos.user.PlayerDto;
import com.youcode.tournoi.dtos.user.UserDto;
import com.youcode.tournoi.entities.*;
import com.youcode.tournoi.exceptions.MatchNotFoundException;
import com.youcode.tournoi.persistence.AdminRepository;
import com.youcode.tournoi.persistence.CommentaireRepository;
import com.youcode.tournoi.persistence.MatchRepository;
import com.youcode.tournoi.persistence.PlayerRepository;
import com.youcode.tournoi.services.interfaces.CommentaireService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentaireServiceImpl implements CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private final ModelMapper modelMapper;
    private final MatchRepository matchRepository;


    public CommentaireServiceImpl(CommentaireRepository commentaireRepository, ModelMapper modelMapper, MatchRepository matchRepository) {
        this.commentaireRepository = commentaireRepository;
        this.modelMapper = modelMapper;
        this.matchRepository = matchRepository;
    }

    public CommentaireReq addCommentaire(CommentaireReq commentaireReq, Player player) {
        Commentaire commentaire = new Commentaire();
        commentaire.setContenu(commentaireReq.getContenu());
        commentaire.setDate(commentaireReq.getDate());
        commentaire.setHeure(commentaireReq.getHeure());
        commentaire.setMatch(matchRepository.findById(commentaireReq.getMatchId()).orElse(null));
        commentaire.setUser(player);

        commentaire = commentaireRepository.save(commentaire);

        CommentaireReq createdCommentaireReq = new CommentaireReq();
        createdCommentaireReq.setIdCommentaire(commentaire.getIdCommentaire());
        createdCommentaireReq.setMatchId(commentaire.getMatch().getIdMatch());
        createdCommentaireReq.setUserId(commentaire.getUser().getIdUser());
        createdCommentaireReq.setContenu(commentaire.getContenu());
        createdCommentaireReq.setDate(commentaire.getDate());
        createdCommentaireReq.setHeure(commentaire.getHeure());


        return createdCommentaireReq;
    }

    @Override
    public List<CommentaireDtoRes> getAll() {
        List<Commentaire> commentaireList = commentaireRepository.findAll();
        return commentaireList.stream()
                .map(commentaire -> modelMapper.map(commentaire, CommentaireDtoRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentaireDtoRes validerCommentaire(Long commentaireId) {
        Commentaire commentaire = commentaireRepository.findById(commentaireId)
                .orElseThrow(() -> new RuntimeException("Commentaire non trouvé avec l'ID : " + commentaireId));
        commentaire.setValide(true);
        commentaire = commentaireRepository.save(commentaire);

        return modelMapper.map(commentaire, CommentaireDtoRes.class);
    }

    @Override
    public void delete(Long id) {
        commentaireRepository.deleteById(id);
    }
}
