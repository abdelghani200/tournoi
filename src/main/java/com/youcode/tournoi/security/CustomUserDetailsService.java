package com.youcode.tournoi.security;

import com.youcode.tournoi.entities.Admin;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.persistence.AdminRepository;
import com.youcode.tournoi.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public CustomUserDetailsService(AdminRepository adminRepository, PlayerRepository playerRepository) {
        this.adminRepository = adminRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Player player = playerRepository.findByEmail(email);
        if (player != null) {
            return player;
        }

        Admin admin = adminRepository.findByEmail(email);
        if (admin != null){
            return admin;
        }

        throw new UsernameNotFoundException("No user found with provided email");
    }
}
