package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.match.MatchDetailsDto;
import com.youcode.tournoi.dtos.match.MatchDto;
import com.youcode.tournoi.dtos.match.MatchDtoRes;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.dtos.tournoi.TournoiDtoRes;
import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.entities.Match;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;
import com.youcode.tournoi.exceptions.MatchNotFoundException;
import com.youcode.tournoi.persistence.MatchRepository;
import com.youcode.tournoi.services.interfaces.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, ModelMapper modelMapper) {
        this.matchRepository = matchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MatchDto save(MatchDto matchDto) {
        Match matchEntity = modelMapper.map(matchDto, Match.class);
        Match savedMatch = matchRepository.save(matchEntity);
        return modelMapper.map(savedMatch, MatchDto.class);
    }

    @Override
    public List<MatchDtoRes> getAll() {
        List<Match> matchList = matchRepository.findAll();
        return matchList.stream()
                .map(match -> modelMapper.map(match, MatchDtoRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public MatchDtoRes findById(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(()->new EquipeNotFoundException("The match with ID " + id + " does not exist"));
        return modelMapper.map(match, MatchDtoRes.class);
    }

    @Override
    public List<MatchDtoRes> getMatchesByTournoiId(Long tournoiId) {
        List<Long> matchIds = matchRepository.findByTournoiId(tournoiId);
        System.out.println(matchIds);
        List<MatchDtoRes> matchDtoResList = new ArrayList<>();
        for (Long matchId : matchIds) {
            Optional<Match> matchOptional = matchRepository.findById(matchId);
            matchOptional.ifPresent(match -> {
                MatchDtoRes matchDtoRes = new MatchDtoRes();

                matchDtoRes.setIdMatch(match.getIdMatch());
                matchDtoRes.setDateMatch(match.getDateMatch());
                matchDtoRes.setHeureMatch(match.getHeureMatch());

                TournoiDtoRes tournoiDtoRes = new TournoiDtoRes();
                tournoiDtoRes.setId(match.getTournoi().getId());
                tournoiDtoRes.setNameTournoi(match.getTournoi().getNameTournoi());

                matchDtoRes.setTournoi(tournoiDtoRes);

                EquipeDtoRes equipe1DtoRes = new EquipeDtoRes();
                equipe1DtoRes.setIdEquipe(match.getEquipe1().getIdEquipe());
                equipe1DtoRes.setNomEquipe(match.getEquipe1().getNomEquipe());
                equipe1DtoRes.setType(match.getEquipe1().getType());

                EquipeDtoRes equipe2DtoRes = new EquipeDtoRes();
                equipe2DtoRes.setIdEquipe(match.getEquipe2().getIdEquipe());
                equipe2DtoRes.setNomEquipe(match.getEquipe2().getNomEquipe());
                equipe2DtoRes.setType(match.getEquipe2().getType());

                matchDtoRes.setEquipe2(equipe2DtoRes);

                matchDtoRes.setEquipe1(equipe1DtoRes);

                matchDtoResList.add(matchDtoRes);
            });
        }

        System.out.println(matchDtoResList);
        return matchDtoResList;
    }

    @Override
    public void delete(Long id) {
        matchRepository.deleteById(id);
    }

    @Override
    public MatchDto update(Long id, MatchDto matchDto) {
        Match matchToUpdate = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + id));

        matchToUpdate.setDateMatch(matchDto.getDateMatch());
        matchToUpdate.setHeureMatch(matchDto.getHeureMatch());

        Match updatedMatch = matchRepository.save(matchToUpdate);
        return modelMapper.map(updatedMatch, MatchDto.class);
    }


}
