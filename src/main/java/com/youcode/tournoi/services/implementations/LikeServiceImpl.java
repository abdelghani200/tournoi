package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.like.LikeDtoReq;
import com.youcode.tournoi.entities.Like;
import com.youcode.tournoi.persistence.LikeRepository;
import com.youcode.tournoi.services.interfaces.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper;

    public LikeServiceImpl(LikeRepository likeRepository, ModelMapper modelMapper) {
        this.likeRepository = likeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LikeDtoReq save(LikeDtoReq likeDtoReq) {
        Like like = modelMapper.map(likeDtoReq, Like.class);
        Like savedLike = likeRepository.save(like);
        return modelMapper.map(savedLike, LikeDtoReq.class);
    }
}
