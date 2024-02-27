package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.but.ButDtoReq;
import com.youcode.tournoi.dtos.but.ButDtoRes;

import java.util.List;

public interface ButService {
    ButDtoReq saveBut(ButDtoReq butDtoReq);
    List<ButDtoRes> getAll();

}
