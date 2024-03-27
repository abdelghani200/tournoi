package com.youcode.tournoi.persistence;

import com.youcode.tournoi.entities.Admin;
import com.youcode.tournoi.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail (String email);

}
