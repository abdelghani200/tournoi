package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.dtos.user.AdminDto;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
        AdminDto createdAdmin = userService.createAdmin(adminDto);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @PostMapping("/player")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto) {
        PlayerDto createdPlayer = userService.createPlayer(playerDto);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @GetMapping("/players")
    @PreAuthorize("hasRole('Admin')")
    public List<PlayerDtoRes> getAllPlayers() {
        return userService.getAllPlayers();
    }

    @GetMapping("admins")
    @PreAuthorize("hasRole('Admin')")
    public List<AdminDto> getAllAdmins(){
        return userService.getAllAdmins();
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('Admin')")
    public PlayerDtoRes searchPlayers(@RequestParam String searchTerm){
        return userService.searchPlayers(searchTerm);
    }

}
