package com.example.demo.agency;

import com.example.demo.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AgencyRepository extends JpaRepository<Agency, Long> {

    @Query("SELECT a FROM Agency a WHERE a.loginUsername = ?1")
    Optional<Agency> findByLoginUsername(String loginUsername);
}