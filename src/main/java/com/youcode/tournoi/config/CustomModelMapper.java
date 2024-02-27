package com.youcode.tournoi.config;

import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.entities.Player;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomModelMapper {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
