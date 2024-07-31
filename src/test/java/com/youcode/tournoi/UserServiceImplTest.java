package com.youcode.tournoi;

import com.youcode.tournoi.dtos.auth.UserDto;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.dtos.user.AdminDto;
import com.youcode.tournoi.entities.Admin;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.exceptions.PlayerNotFoundException;
import com.youcode.tournoi.persistence.AdminRepository;
import com.youcode.tournoi.persistence.PlayerRepository;
import com.youcode.tournoi.services.implementations.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private AdminRepository adminRepository;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    private Admin admin;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(modelMapper.map(any(AdminDto.class), eq(Admin.class))).thenReturn(new Admin());
        Mockito.lenient().when(modelMapper.map(any(PlayerDto.class), eq(Player.class))).thenReturn(new Player());
        Mockito.lenient().when(modelMapper.map(any(Player.class), eq(PlayerDtoRes.class))).thenReturn(new PlayerDtoRes());
        Mockito.lenient().when(modelMapper.map(any(Admin.class), eq(AdminDto.class))).thenReturn(new AdminDto());
    }


    @Test
    void testCreateAdmin() {
        // Given
        AdminDto adminDto = new AdminDto();
        Admin adminEntity = new Admin();
        when(modelMapper.map(adminDto, Admin.class)).thenReturn(adminEntity);
        when(adminRepository.save(adminEntity)).thenReturn(adminEntity);
        when(modelMapper.map(adminEntity, AdminDto.class)).thenReturn(adminDto);

        // When
        AdminDto savedAdminDto = userService.createAdmin(adminDto);

        // Then
        assertEquals(adminDto, savedAdminDto);
        verify(adminRepository, times(1)).save(adminEntity);
    }

    @Test
    void testCreatedPlayer(){
        PlayerDto playerDto = new PlayerDto();
        Player playerEntity = new Player();

        when(modelMapper.map(playerDto, Player.class)).thenReturn(playerEntity);
        when(playerRepository.save(playerEntity)).thenReturn(playerEntity);
        when(modelMapper.map(playerEntity, PlayerDto.class)).thenReturn(playerDto);

        PlayerDto savedPlayer = userService.createPlayer(playerDto);

        assertEquals(playerDto, savedPlayer);
        verify(playerRepository, times(1)).save(playerEntity);
    }

    @Test
    void testFindById() {
        // Given
        Long id = 1L;
        Player player = new Player();
        player.setIdUser(1L);
        when(playerRepository.findById(id)).thenReturn(Optional.of(player));

        PlayerDtoRes expectedPlayerDtoRes = new PlayerDtoRes();
        expectedPlayerDtoRes.setIdUser(id);
        when(modelMapper.map(player, PlayerDtoRes.class)).thenReturn(expectedPlayerDtoRes);

        // When
        PlayerDtoRes playerDtoRes = userService.findById(id);

        // Then
        assertEquals(id, playerDtoRes.getIdUser());
    }

    @Test
    void testGetUserByEmail_Player() {
        // Given
        String email = "player@example.com";
        Player player = new Player();
        player.setEmail(email);
        when(playerRepository.findByEmail(email)).thenReturn(player);

        // When
        UserDto userDto = userService.getUserByEmail(email);

        // Then
        assertEquals(email, userDto.getEmail());
        assertEquals("PLAYER", userDto.getRole());
    }

    @Test
    void testGetUserByEmail_NotFound() {
        // Given
        String email = "unknown@example.com";
        when(playerRepository.findByEmail(email)).thenReturn(null);
        when(adminRepository.findByEmail(email)).thenReturn(null);

        // When
        UserDto userDto = userService.getUserByEmail(email);

        // Then
        assertEquals(null, userDto);
    }

    @Test
    void testSearchPlayers() {
        // Given
        String searchTerm = "searchTerm";
        List<Player> searchResults = new ArrayList<>();
        searchResults.add(new Player());
        when(playerRepository.searchPlayers(searchTerm)).thenReturn(searchResults);

        PlayerDtoRes expectedPlayerDtoRes = new PlayerDtoRes();
        expectedPlayerDtoRes.setIdUser(1L);
        when(modelMapper.map(any(Player.class), eq(PlayerDtoRes.class))).thenReturn(expectedPlayerDtoRes);

        // When
        PlayerDtoRes playerDtoRes = userService.searchPlayers(searchTerm);

        assertEquals(1L, playerDtoRes.getIdUser());
    }

    @Test
    void testSearchPlayers_NoResults() {
        // Given
        String searchTerm = "unknownTerm";
        when(playerRepository.searchPlayers(searchTerm)).thenReturn(new ArrayList<>());

        // When & Then
        assertThrows(PlayerNotFoundException.class, () -> userService.searchPlayers(searchTerm));
    }


    @Test
    void testSearchPlayers_MultipleResults() {
        // Given
        String searchTerm = "commonTerm";
        List<Player> searchResults = new ArrayList<>();
        searchResults.add(new Player());
        searchResults.add(new Player());
        when(playerRepository.searchPlayers(searchTerm)).thenReturn(searchResults);

        PlayerDtoRes expectedPlayerDtoRes = new PlayerDtoRes();
        expectedPlayerDtoRes.setIdUser(1L);

        when(modelMapper.map(any(Player.class), eq(PlayerDtoRes.class))).thenReturn(expectedPlayerDtoRes);

        // When
        PlayerDtoRes playerDtoRes = userService.searchPlayers(searchTerm);

        assertEquals(1L, playerDtoRes.getIdUser());
    }


    @Test
    void testSearchPlayers_EmptySearchTerm() {
        // Given
        String searchTerm = "";
        // Configurez le mock playerRepository pour retourner une liste vide puisque le terme de recherche est vide
        when(playerRepository.searchPlayers(searchTerm)).thenReturn(new ArrayList<>());

        assertThrows(PlayerNotFoundException.class, () -> userService.searchPlayers(searchTerm));
    }

    @Test
    @DisplayName("Test searchPlayers with term not found")
    void testSearchPlayers_TermNotFound() {
        // Given
        String searchTerm = "unknownTerm";
        when(playerRepository.searchPlayers(searchTerm)).thenReturn(new ArrayList<>());

        assertThrows(PlayerNotFoundException.class, () -> userService.searchPlayers(searchTerm), "PlayerNotFoundException should be thrown when no players are found with the search term: " + searchTerm);
    }



}
