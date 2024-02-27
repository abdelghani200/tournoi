package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.carton.CartonDtoReq;
import com.youcode.tournoi.entities.Carton;
import com.youcode.tournoi.persistence.CartonRepository;
import com.youcode.tournoi.services.interfaces.CartonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CartonServiceImpl implements CartonService {

    private final CartonRepository cartonRepository;
    private final ModelMapper modelMapper;

    public CartonServiceImpl(CartonRepository cartonRepository, ModelMapper modelMapper) {
        this.cartonRepository = cartonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CartonDtoReq save(CartonDtoReq cartonDtoReq) {
        Carton cartonEntity = modelMapper.map(cartonDtoReq, Carton.class);
        Carton savedCarton = cartonRepository.save(cartonEntity);
        return modelMapper.map(savedCarton, CartonDtoReq.class);
    }
}
