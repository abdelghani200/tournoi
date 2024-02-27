package com.youcode.tournoi.dtos.tournoi;

import com.youcode.tournoi.enums.TypeTournoi;
import lombok.Data;

import java.util.Date;

@Data
public class TournoiDto {
    private Long id;
    private String nameTournoi;
    private TypeTournoi type;
    private Date dateDebut;
    private Date dateFin;
    private Long adminId = 1L;
}
