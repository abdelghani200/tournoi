package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.dtos.user.AdminDto;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.entities.Admin;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.exceptions.PlayerNotFoundException;
import com.youcode.tournoi.persistence.AdminRepository;
import com.youcode.tournoi.persistence.PlayerRepository;
import com.youcode.tournoi.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final AdminRepository adminRepository;
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(AdminRepository adminRepository, PlayerRepository playerRepository, ModelMapper modelMapper){
        this.adminRepository = adminRepository;
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        Admin adminEntity = modelMapper.map(adminDto, Admin.class);
        Admin savedAdmin = adminRepository.save(adminEntity);
        return modelMapper.map(savedAdmin, AdminDto.class);
    }

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player playerEntity = modelMapper.map(playerDto, Player.class);
        Player savedplayer = playerRepository.save(playerEntity);
        return modelMapper.map(savedplayer, PlayerDto.class);
    }

    @Override
    public List<PlayerDtoRes> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream()
                .map(player -> modelMapper.map(player, PlayerDtoRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> adminList = adminRepository.findAll();
        return adminList.stream()
                .map(admin -> modelMapper.map(admin, AdminDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDtoRes findById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(()->new PlayerNotFoundException("The player with ID " + id + " does not exist"));
        return modelMapper.map(player, PlayerDtoRes.class);
    }



}
