package com.youcode.tournoi.dtos.like;

import lombok.Data;

@Data
public class LikeDtoReq {
    private Long idLike;
    private Long userId;
    private Long matchId;
}
