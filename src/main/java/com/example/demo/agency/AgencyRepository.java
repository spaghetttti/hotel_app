package com.example.demo.agency;

import com.example.demo.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface AgencyRepository extends JpaRepository<Agency, Long> {
}