package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.match.*;
import com.youcode.tournoi.entities.Match;
import com.youcode.tournoi.entities.MatchFB;
import com.youcode.tournoi.entities.MatchFifa;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;
import com.youcode.tournoi.exceptions.MatchNotFoundException;
import com.youcode.tournoi.exceptions.PlayerNotFoundException;
import com.youcode.tournoi.persistence.MatchFBRepository;
import com.youcode.tournoi.persistence.MatchFifaRepository;
import com.youcode.tournoi.persistence.PlayerRepository;
import com.youcode.tournoi.services.interfaces.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final ModelMapper modelMapper;
    private final MatchFBRepository matchFBRepository;
    private final MatchFifaRepository matchFifaRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public MatchServiceImpl(ModelMapper modelMapper, MatchFBRepository matchFBRepository, MatchFifaRepository matchFifaRepository, PlayerRepository playerRepository) {
        this.modelMapper = modelMapper;
        this.matchFBRepository = matchFBRepository;
        this.matchFifaRepository = matchFifaRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public MatchFbDto save(MatchFbDto matchFbDto) {
        MatchFB matchEntity = modelMapper.map(matchFbDto, MatchFB.class);
        MatchFB savedMatch = matchFBRepository.save(matchEntity);
        return modelMapper.map(savedMatch, MatchFbDto.class);
    }

    @Override
    public MatchFifaDto save(MatchFifaDto matchFifaDto) {

        Player player1 = playerRepository.findById(matchFifaDto.getPlayer1Id())
                .orElseThrow(() -> new PlayerNotFoundException("Player with ID " + matchFifaDto.getPlayer1Id() + " not found"));
        Player player2 = playerRepository.findById(matchFifaDto.getPlayer2Id())
                .orElseThrow(() -> new PlayerNotFoundException("Player with ID " + matchFifaDto.getPlayer2Id() + " not found"));

        MatchFifa matchFifa = modelMapper.map(matchFifaDto, MatchFifa.class);

        matchFifa.setPlayer1(player1);
        matchFifa.setPlayer2(player2);

        MatchFifa savedFifa = matchFifaRepository.save(matchFifa);

        return modelMapper.map(savedFifa, MatchFifaDto.class);
    }


    @Override
    public List<MatchFbDtoRes> getAll() {
        List<MatchFB> matchList = matchFBRepository.findAll();
        List<MatchFifa> matchFifa = matchFifaRepository.findAll();
        List<MatchFbDtoRes> resultList = new ArrayList<>();

        resultList.addAll(matchList.stream()
                .map(match -> modelMapper.map(match, MatchFbDtoRes.class))
                .toList());

        resultList.addAll(matchFifa.stream()
                .map(match -> modelMapper.map(match, MatchFbDtoRes.class))
                .toList());

        return resultList;
    }

    @Override
    public List<MatchDtoRes> getMatchesByTournoiId(Long tournoiId) {
        List<Match> matchList = new ArrayList<>();
        matchList.addAll(matchFBRepository.findByTournoiId(tournoiId));
        matchList.addAll(matchFifaRepository.findByTournoiId(tournoiId));

        return matchList.stream()
                .map(match -> modelMapper.map(match, MatchDtoRes.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<MatchFifaDtoRes> getAllFifa() {
        return null;
    }

    public List<MatchFbDtoRes> getMatchesFbByTournoiId(Long tournoiId) {
        List<MatchFB> matchList = matchFBRepository.findByTournoiId(tournoiId);
        return matchList.stream()
                .map(match -> modelMapper.map(match, MatchFbDtoRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchFifaDtoRes> getMatchesFifaByTournoi(Long tournoiId) {
        return null;
    }


    @Override
    public MatchFbDtoRes findById(Long id) {
        Match match = matchFBRepository.findById(id)
                .orElseThrow(()->new EquipeNotFoundException("The match with ID " + id + " does not exist"));
        return modelMapper.map(match, MatchFbDtoRes.class);
    }


    @Override
    public void delete(Long id) {
        matchFBRepository.deleteById(id);
    }

    @Override
    public MatchFbDto update(Long id, MatchFbDto matchDto) {
        MatchFB matchToUpdate = matchFBRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + id));

        matchToUpdate.setScoreEquipe1(matchDto.getScoreEquipe1());
        matchToUpdate.setScoreEquipe2(matchDto.getScoreEquipe2());

        MatchFB updatedMatch = matchFBRepository.save(matchToUpdate);
        return modelMapper.map(updatedMatch, MatchFbDto.class);
    }



    //    @Override
//    public List<MatchDtoRes> getMatchesByTournoiId(Long tournoiId) {
//        List<Long> matchIds = matchRepository.findByTournoiId(tournoiId);
//        System.out.println(matchIds);
//        List<MatchDtoRes> matchDtoResList = new ArrayList<>();
//        for (Long matchId : matchIds) {
//            Optional<Match> matchOptional = matchRepository.findById(matchId);
//            matchOptional.ifPresent(match -> {
//                MatchDtoRes matchDtoRes = new MatchDtoRes();
//
//                matchDtoRes.setIdMatch(match.getIdMatch());
//                matchDtoRes.setDateMatch(match.getDateMatch());
//                matchDtoRes.setHeureMatch(match.getHeureMatch());
//
//                TournoiDtoRes tournoiDtoRes = new TournoiDtoRes();
//                tournoiDtoRes.setId(match.getTournoi().getId());
//                tournoiDtoRes.setNameTournoi(match.getTournoi().getNameTournoi());
//
//                matchDtoRes.setTournoi(tournoiDtoRes);
//
//                EquipeDtoRes equipe1DtoRes = new EquipeDtoRes();
//                equipe1DtoRes.setIdEquipe(match.getEquipe1().getIdEquipe());
//                equipe1DtoRes.setNomEquipe(match.getEquipe1().getNomEquipe());
//                equipe1DtoRes.setType(match.getEquipe1().getType());
//
//                EquipeDtoRes equipe2DtoRes = new EquipeDtoRes();
//                equipe2DtoRes.setIdEquipe(match.getEquipe2().getIdEquipe());
//                equipe2DtoRes.setNomEquipe(match.getEquipe2().getNomEquipe());
//                equipe2DtoRes.setType(match.getEquipe2().getType());
//
//                matchDtoRes.setEquipe2(equipe2DtoRes);
//
//                matchDtoRes.setEquipe1(equipe1DtoRes);
//
//                matchDtoResList.add(matchDtoRes);
//            });
//        }
//
//        System.out.println(matchDtoResList);
//        return matchDtoResList;
//    }


}
