package com.youcode.tournoi.web.auth;

import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    private final AuthService authService;
    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<PlayerDto> registerUser(@RequestBody PlayerDto memberDto) {
        PlayerDto registeredMember = authService.registerUser(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredMember);
    }

}
