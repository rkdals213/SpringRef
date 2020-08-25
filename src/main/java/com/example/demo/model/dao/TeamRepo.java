package com.example.demo.model.dao;

import com.example.demo.model.dto.Member;
import com.example.demo.model.dto.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepo extends JpaRepository<Team, String> {
    List<Team> findAll();
}
