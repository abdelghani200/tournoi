package com.youcode.tournoi.dtos.tournoi;

import com.youcode.tournoi.dtos.user.AdminDto;
import com.youcode.tournoi.enums.TypeTournoi;
import lombok.Data;

import java.util.Date;

@Data
public class TournoiDtoRes {
    private Long id;
    private String nameTournoi;
    private TypeTournoi type;
    private Date dateDebut;
    private Date dateFin;
    private AdminDto admin;
}
