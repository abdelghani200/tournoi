package com.youcode.tournoi.security;

import com.youcode.tournoi.dtos.auth.AuthResponse;
import com.youcode.tournoi.dtos.auth.LoginRequest;
import com.youcode.tournoi.dtos.auth.UserDto;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.entities.User;
import com.youcode.tournoi.services.interfaces.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(PasswordEncoder passwordEncoder, UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto user = userService.getUserByEmail(loginRequest.getEmail());
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.generateToken(userDetails, role));
        authResponse.setUser(user);

        return authResponse;
    }

    public PlayerDto registerUser(PlayerDto playerDto) {
        return userService.createPlayer(playerDto);
    }

}
