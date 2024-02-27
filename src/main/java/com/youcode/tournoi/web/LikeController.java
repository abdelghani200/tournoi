package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.like.LikeDtoReq;
import com.youcode.tournoi.services.interfaces.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<LikeDtoReq> createLike(@RequestBody LikeDtoReq likeDtoReq){
        LikeDtoReq createdLike = likeService.save(likeDtoReq);
        return  new ResponseEntity<>(createdLike, HttpStatus.CREATED);
    }
}
